package com.davidmiguel.photoeditor.filters.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Contrast function filter. 
 * value > 1 -> increase contrast
 * value < 1 -> decrease contrast
 * http://pippin.gimp.org/image_processing/chap_point.html#id2557812
 */
public class ContrastFilter extends FunctionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public ContrastFilter(double value) {
		super(value);
	}

	@Override
	protected void createLookupTable(double value) {
		for (int i = 0; i < BITS; i++) {
			lookupTable[i] = (short) ((i - (BITS / 2)) * (value) + (BITS / 2));
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
		logger.info("Apply ContrastFilter");
		return super.apply(input);
	}
}
