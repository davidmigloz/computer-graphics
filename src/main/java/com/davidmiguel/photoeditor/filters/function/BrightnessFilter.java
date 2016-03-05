package com.davidmiguel.photoeditor.filters.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Brightness function filter. 
 * value > 0 -> increase brightness
 * value < 0 -> decrease brightness
 * http://pippin.gimp.org/image_processing/chap_point.html#id2556807
 */
public class BrightnessFilter extends FunctionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public BrightnessFilter(double value) {
		super(value);
	}

	@Override
	protected void createLookupTable(double value) {
		for (int i = 0; i < BITS; i++) {
			lookupTable[i] = (short) (i + value);
			// Check value in [0, BITS)
			if (lookupTable[i] >= BITS) {
				lookupTable[i] = BITS - 1;
			} else if (lookupTable[i] < 0) {
				lookupTable[i] = 0;
			}
		}
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply BrightnessFilter");
		return super.apply(input);
	}
}
