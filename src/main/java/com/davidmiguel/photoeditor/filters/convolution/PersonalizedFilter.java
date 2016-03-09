package com.davidmiguel.photoeditor.filters.convolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

public class PersonalizedFilter extends ConvolutionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public PersonalizedFilter(double[][] matrix) {
		super(matrix, 1, 0);
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply PersonalizedFilter");
		return super.apply(input);
	}
}