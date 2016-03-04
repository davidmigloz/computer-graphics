package com.davidmiguel.photoeditor.filters;

import javafx.scene.image.Image;

public interface Filter {
	
	/**
	 * 
	 */
	static final short BITS = 256;

	/**
	 * Apply the filter to the given image and return a new image with the
	 * result.
	 * 
	 * @param input
	 *            image to transform
	 * @return new image
	 */
	public Image apply(Image input);

}