package com.davidmiguel.photoeditor.filters.dithering;

import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.function.GrayscaleFilter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Random Dithering. For each pixel a random threshold is chosen.
 */
public class RandomDitheringFilter extends DitheringFilter {

	private Random rand;
	private SortedSet<Integer> thresholds;
	private int[] levels;

	public RandomDitheringFilter(int k) {
		super(k);
		rand = new Random();
		thresholds = new TreeSet<>();
		levels = calculateLevels();

	}

	@Override
	public Image apply(Image input) {
		// Conver to grayscale
		Filter filter = new GrayscaleFilter();
		input = filter.apply(input);

		// Reader for the original image
		PixelReader pixelReader = input.getPixelReader();
		int width = (int) input.getWidth();
		int height = (int) input.getHeight();

		// Create new image and get its writer
		WritableImage result = new WritableImage(width, height);
		PixelWriter pixelWriter = result.getPixelWriter();

		// Apply transformation
		int val, newVal;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Actual color
				Color color = pixelReader.getColor(x, y);
				// Get pixel value in range [0,BITS)
				val = (int) (color.getRed() * (BITS - 1));
				// New value
				newVal = calculateNewValue(val);
				Color newColor = Color.rgb(newVal, newVal, newVal,
						color.getOpacity());
				// Write new pixel
				pixelWriter.setColor(x, y, newColor);
			}
		}
		return result;
	}

	/**
	 * Calculate the values of each level of gray that the resulting image is
	 * going to have.
	 */
	private int[] calculateLevels() {
		int[] levels = new int[k];
		for (int i = 0; i < k; i++) {
			levels[i] = (int) Math.round((BITS * i) / (double) k);
		}
		return levels;
	}

	/**
	 * Calculare the new value (contained in levels) for a given value.
	 */
	private int calculateNewValue(int val) {
		// Clear previous random thresholds
		thresholds.clear();
		// Get new random thresholds
		for (int i = 0; i < k - 1; i++) {
			Integer t = rand.nextInt(BITS);
			while (thresholds.contains(t)) {
				t = rand.nextInt(BITS);
			}
			thresholds.add(t);
		}
		thresholds.add((int) BITS); // add upper threshold
		// Get new val
		int newVal = val;
		Iterator<Integer> it = thresholds.iterator();
		for (int i = 0; i < k; i++) { // Iterate over thresholds
			int threshold = it.next();
			if (val < threshold) {
				newVal = levels[i];
				break;
			}
		}
		return newVal;
	}
}