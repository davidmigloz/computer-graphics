package com.davidmiguel.photoeditor.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Brightness function filter.
 */
public class BrightnessFilter extends FunctionFilter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public BrightnessFilter(int value) {
		super(value);
	}

	@Override
	protected void createLookupTable(int value) {
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
		logger.info("Apply functional filter");
		return super.apply(input);
	}
}
