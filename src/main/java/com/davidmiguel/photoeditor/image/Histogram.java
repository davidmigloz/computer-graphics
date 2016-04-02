package com.davidmiguel.photoeditor.image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * Calculate the histogram of an image.
 */
public class Histogram {
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private static final short BITS = 256;

	private List<Integer> r;
	private List<Integer> g;
	private List<Integer> b;

	public Histogram(Image image) {
		r = new ArrayList<>(Collections.nCopies(BITS, 0));
		g = new ArrayList<>(Collections.nCopies(BITS, 0));
		b = new ArrayList<>(Collections.nCopies(BITS, 0));
		// Calculate frequency of each level
		PixelReader pixelReader = image.getPixelReader();
		int valR, valG, valB;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				// Actual color
				Color color = pixelReader.getColor(x, y);
				// from [0,1) to [0,BITS)
				valR = (int) (color.getRed() * (BITS - 1));
				valG = (int) (color.getGreen() * (BITS - 1));
				valB = (int) (color.getBlue() * (BITS - 1));
				// Increase counter
				r.set(valR, r.get(valR) + 1);
				g.set(valG, g.get(valG) + 1);
				b.set(valB, b.get(valB) + 1);
			}
		}
		// Calculare max value
		int maxR = Collections.max(r);
		int maxG = Collections.max(g);
		int maxB = Collections.max(b);
		int max = Math.max(Math.max(maxR, maxG), maxB);
		//logger.debug("Max: {}", max);
		// Scale vaues: maxnumber -> 100%
		for (int i = 0; i < BITS; i++) {
			r.set(i, (int) ((r.get(i) / (double) max) * 100));
			g.set(i, (int) ((g.get(i) / (double) max) * 100));
			b.set(i, (int) ((b.get(i) / (double) max) * 100));
			//logger.debug("({},{},{})", r.get(i), g.get(i), b.get(i));
		}
	}

	/**
	 * Get the percentage of red values. (0->0%; maxValue->100%)
	 */
	public List<Integer> getRed() {
		return r;
	}

	/**
	 * Get the percentage of green values. (0->0%; maxValue->100%)
	 */
	public List<Integer> getGreen() {
		return g;
	}

	/**
	 * Get the percentage of blue values. (0->0%; maxValue->100%)
	 */
	public List<Integer> getBlue() {
		return b;
	}
}