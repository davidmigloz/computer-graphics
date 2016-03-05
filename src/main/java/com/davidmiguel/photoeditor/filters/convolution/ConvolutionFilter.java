package com.davidmiguel.photoeditor.filters.convolution;

import com.davidmiguel.photoeditor.filters.Filter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Functional filter (Blur, Gausian Blur, Sharpen, Edges, Emboss).
 * http://pippin.gimp.org/image_processing/chap_area.html#id2559218
 */
public class ConvolutionFilter implements Filter {

	protected double[][] kernel;
	protected double divisor;
	protected double offset;
	
	public ConvolutionFilter(double[][] kernel, double divisor, double offset) {
		this.kernel = kernel;
		this.divisor = divisor;
		this.offset = offset;
	}

	@Override
	public Image apply(Image input) {
		// Reader for the original image
		PixelReader pixelReader = input.getPixelReader();
		int width = (int) input.getWidth();
		int height = (int) input.getHeight();

		// Create new image and get its writer
		WritableImage result = new WritableImage(width, height);
		PixelWriter pixelWriter = result.getPixelWriter();

		// Apply transformation (without borders)
		int tamMidKernel = (kernel.length - 1) / 2;
		Color[][] pixels = new Color[kernel.length][kernel.length];
		int newR, newG, newB, sumR, sumG, sumB;
		double a;
		Color newColor;
		for (int y = 0; y < input.getHeight() - (kernel.length - 1); y++) {
			for (int x = 0; x < input.getWidth() - (kernel.length - 1); x++) {
				// Get pixel matrix
				for (int i = 0; i < kernel.length; ++i) {
					for (int j = 0; j < kernel.length; ++j) {
						pixels[i][j] = pixelReader.getColor(x + i, y + j);
					}
				}
				// Get sum of RGB multiplied by kernel
				sumR = sumG = sumB = 0;
				for (int i = 0; i < kernel.length; ++i) {
					for (int j = 0; j < kernel.length; ++j) {
						// (from [0,1) to [0,BITS)) * kernel
						sumR += (pixels[i][j].getRed() * (BITS - 1))
								* kernel[i][j];
						sumG += (pixels[i][j].getGreen() * (BITS - 1))
								* kernel[i][j];
						sumB += (pixels[i][j].getBlue() * (BITS - 1))
								* kernel[i][j];
					}
				}
				// Get final RGB
				newR = (int) (sumR / divisor + offset);
				newR = checkInRange(newR);
				newG = (int) (sumG / divisor + offset);
				newG = checkInRange(newG);
				newB = (int) (sumB / divisor + offset);
				newB = checkInRange(newB);
				a = pixels[tamMidKernel + 1][tamMidKernel + 1].getOpacity();
				newColor = Color.rgb(newR, newG, newB, a);
				// Write new pixel
				pixelWriter.setColor(x + tamMidKernel, y + tamMidKernel,
						newColor);
			}
		}

		// Copy borders from original image
		for (int i = 0; i < input.getWidth(); i++) {			
			for (int j = 0; j < tamMidKernel; j++) {
				// Top border
				pixelWriter.setColor(i, j, pixelReader.getColor(i, j));
				// Botton border
				pixelWriter.setColor(i, (int) (input.getHeight() - 1 - j),
						pixelReader.getColor(i, (int) (input.getHeight() - 1 - j)));
			}
		}
		for (int i = 0; i < input.getHeight(); i++) {
			for (int j = 0; j < tamMidKernel; j++) {
				// Right border
				pixelWriter.setColor((int) (input.getWidth() - 1 - j), i,
						pixelReader.getColor((int) (input.getWidth() - 1 - j), i));
				
				// Left border
				pixelWriter.setColor(j, i, pixelReader.getColor(j, i));		
			}	
		}

		return result;
	}

	/**
	 * Check the pixel is in the range [0,BITS).
	 * 
	 * @param pixel
	 * @return pixel if in range; 0 if < 0; 255 if > 255
	 */
	private int checkInRange(int pixel) {
		if (pixel < 0) {
			return 0;
		} else if (pixel >= BITS) {
			return BITS - 1;
		} else {
			return pixel;
		}
	}
}