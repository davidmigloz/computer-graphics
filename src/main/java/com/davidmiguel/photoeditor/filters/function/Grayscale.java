package com.davidmiguel.photoeditor.filters.function;

import com.davidmiguel.photoeditor.filters.Filter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Convert a color image to grayscale with a Colorimetric (luminance-preserving) conversion.
 * Averages the values taking into account human perception. The formula for luminosity is: 
 * L = 0.21 R + 0.72 G + 0.07 B
 * https://en.wikipedia.org/wiki/Grayscale#Colorimetric_.28luminance-preserving.29_conversion_to_grayscale 
 */
public class Grayscale implements Filter {

	@Override
	public Image apply(Image input) {
		// Reader for the original image
		PixelReader pixelReader = input.getPixelReader();
		int width = (int) input.getWidth();
		int height = (int) input.getHeight();

		// Create new image and get its writer
		WritableImage result = new WritableImage(width, height);
		PixelWriter pixelWriter = result.getPixelWriter();

		// Apply transformation
		double newVal;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Actual color
				Color color = pixelReader.getColor(x, y);
				// Weighted sums according to human perception
				newVal = 0.2126 * color.getRed() + 0.7152 * color.getGreen()
						+ 0.0722 * color.getBlue();
				// New color
				Color newColor = Color.color(newVal, newVal, newVal,
						color.getOpacity());
				// Write new pixel
				pixelWriter.setColor(x, y, newColor);
			}
		}

		return result;
	}
}