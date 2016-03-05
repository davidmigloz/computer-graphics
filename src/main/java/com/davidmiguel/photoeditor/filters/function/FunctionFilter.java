package com.davidmiguel.photoeditor.filters.function;

import com.davidmiguel.photoeditor.filters.Filter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Functional filter (Brightness, Constrast, Gamma, Inversion).
 */
public abstract class FunctionFilter implements Filter {

	protected short[] lookupTable;

	public FunctionFilter(double value) {
		lookupTable = new short[BITS];
		createLookupTable(value);
	}

	/**
	 * Compute the lookup table for the given value.
	 */
	protected abstract void createLookupTable(double value);

	@Override
	public Image apply(Image input) {
		// Reader for the original image
		PixelReader pixelReader = input.getPixelReader();
		int width = (int) input.getWidth();
		int height = (int) input.getHeight();

		// Create new image and get its writer
		WritableImage result = new WritableImage(width, height);
		PixelWriter pixelWriter = result.getPixelWriter();

		// Apply transformation with the lookup table
		int r, g, b, newR, newG, newB;
		double a;
		for (int y = 0; y < input.getHeight(); y++) {
			for (int x = 0; x < input.getWidth(); x++) {
				// Actual color
				Color color = pixelReader.getColor(x, y);
				// from [0,1) to [0,BITS)
				r = (int) (color.getRed() * (BITS - 1));
				g = (int) (color.getGreen() * (BITS - 1));
				b = (int) (color.getBlue() * (BITS - 1));
				a = color.getOpacity();
				// New color
				newR = lookupTable[r];
				newG = lookupTable[g];
				newB = lookupTable[b];
				Color newColor = Color.rgb(newR, newG, newB, a);
				// Write new pixel
				pixelWriter.setColor(x, y, newColor);
			}
		}
		
		return result;
	}
}