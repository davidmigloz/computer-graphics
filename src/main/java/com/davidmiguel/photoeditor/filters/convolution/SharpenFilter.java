package com.davidmiguel.photoeditor.filters.convolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Sharpen convolution filter. 
 * value < 5  -> 3x3 high-pass sharpen kernel
 * value >= 5 -> 3x3 mean removal sharpen kernel 
 * http://pippin.gimp.org/image_processing/chap_area.html#id2559218
 */
public class SharpenFilter extends ConvolutionFilter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static double OFFSET = 0;
	private final static double DIVISOR = 1;
	private final static double[][] KERNEL_HIGHPASS = {{  0, -1,  0 }, 
													   { -1,  5, -1 },
													   {  0, -1,  0 }};
	private final static double[][] KERNEL_MEANREMOVAL = {{ -1, -1, -1 }, 
														  { -1,  9, -1 },
														  { -1, -1, -1 }};

	public SharpenFilter(int value) {
		super(value < 5 ? KERNEL_HIGHPASS : KERNEL_MEANREMOVAL, DIVISOR, OFFSET);
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply SharpenFilter");
		return super.apply(input);
	}
}