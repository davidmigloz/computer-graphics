package com.davidmiguel.photoeditor.filters.convolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Edge detection convolution filter. 
 * orientation = H  -> horizontal edges
 * orientation = V  -> vertical edges
 * orientation = D  -> diagonal edges
 * http://pippin.gimp.org/image_processing/chap_area.html#id2559218
 */
public class EdgeFilter extends ConvolutionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static double OFFSET = 0;
	private final static double DIVISOR = 1;
	private final static double[][] KERNEL_V = {{ 0, -1, 0 }, 
											    { 0,  1, 0 },
											    { 0,  0, 0 }};
	private final static double[][] KERNEL_H = {{  0, 0, 0 }, 
												{ -1, 1, 0 },
												{  0, 0, 0 }};
	private final static double[][] KERNEL_D = {{ -1, 0, 0 }, 
												{  0, 1, 0 },
												{  0, 0, 0 }};

	public EdgeFilter(char orientation) {
		super(chooseKernel(orientation), DIVISOR, OFFSET);
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply EdgeFilter");
		return super.apply(input);
	}
	
	private static double[][] chooseKernel(char orientation){
		if (orientation == 'V') {
			return KERNEL_V;
		} else if (orientation == 'H') {
			return KERNEL_H;
		} else {
			return KERNEL_D;
		}
	}
}