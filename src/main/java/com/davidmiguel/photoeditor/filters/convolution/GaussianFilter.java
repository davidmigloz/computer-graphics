package com.davidmiguel.photoeditor.filters.convolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Gaussian convolution filter. 
 * value < 5  -> kernel 3x3 
 * value >= 5 -> kernel 5x5
 * http://pippin.gimp.org/image_processing/chap_area.html#id2559346
 */
public class GaussianFilter extends ConvolutionFilter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static double OFFSET = 0;
	private final static double DIVISOR3 = 16;
	private final static double DIVISOR5 = 256;
	private final static double[][] KERNEL3 = {{ 1, 2, 1 }, 
											   { 2, 4, 2 },
											   { 1, 2, 1 }};
	private final static double[][] KERNEL5 = {{ 1, 4,  6,  4,  1 },
											   { 4, 16, 24, 16, 4 }, 
											   { 6, 24, 36, 24, 6 }, 
											   { 4, 16, 24, 16, 4 }, 
											   { 1, 4,  6,  4,  1 }};

	public GaussianFilter(int value) {
		super(value < 5 ? KERNEL3 : KERNEL5,
			  value < 5 ? DIVISOR3 : DIVISOR5,
			  OFFSET);
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply GaussianFilter");
		return super.apply(input);
	}
}
