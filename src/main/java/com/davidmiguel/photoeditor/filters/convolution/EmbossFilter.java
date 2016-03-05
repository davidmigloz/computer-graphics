package com.davidmiguel.photoeditor.filters.convolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Emboss convolution filter. 
 * direction = N  -> nord
 * direction = E  -> east
 * direction = S  -> south
 * direction = W  -> west
 * http://pippin.gimp.org/image_processing/chap_area.html#id2559218
 */
public class EmbossFilter extends ConvolutionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static double OFFSET = 0;
	private final static double DIVISOR = 1;
	private final static double[][] KERNEL_N = {{  1,  1,  1 }, 
											    {  0,  1,  0 },
											    { -1, -1, -1 }};
	private final static double[][] KERNEL_E = {{ -1, 0, 1 }, 
												{ -1, 1, 1 },
												{ -1, 0, 1 }};
	private final static double[][] KERNEL_S = {{ -1, -1, -1 }, 
												{  0,  1,  0 },
												{  1,  1,  1 }};
	private final static double[][] KERNEL_W = {{  1, 0, -1 }, 
												{  1, 1, -1 },
												{  1, 0, -1 }};	

	public EmbossFilter(char direction) {
		super(chooseKernel(direction), DIVISOR, OFFSET);
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply EmbossFilter");
		return super.apply(input);
	}
	
	private static double[][] chooseKernel(char direction){
		if (direction == 'N') {
			return KERNEL_N;
		} else if (direction == 'E') {
			return KERNEL_E;
		} else if (direction == 'S') {
			return KERNEL_S;
		} else {
			return KERNEL_W;
		}
	}
}