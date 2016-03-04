package com.davidmiguel.photoeditor.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Inversion function filter.
 * http://pippin.gimp.org/image_processing/chap_point.html#id2557324
 */
public class InversionFilter extends FunctionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public InversionFilter() {
		super(BITS - 1);
	}

	@Override
	protected void createLookupTable(double value) {
		for (int i = 0; i < BITS; i++) {
			lookupTable[i] = (short) (value - i);
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
		logger.info("Apply InversionFilter");
		return super.apply(input);
	}
}
