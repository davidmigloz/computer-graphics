package com.davidmiguel.photoeditor.filters.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Gamma function filter. 
 * value > 1 -> lighten 
 * value < 1 -> darken
 * http://pippin.gimp.org/image_processing/chap_point.html#id2557383
 */
public class GammaFilter extends FunctionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public GammaFilter(double value) {
		super(value);
	}

	@Override
	protected void createLookupTable(double value) {
		for (int i = 0; i < BITS; i++) {
			lookupTable[i] = (int) Math.pow(i, value);
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
		logger.info("Apply GammaFilter");
		return super.apply(input);
	}
}
