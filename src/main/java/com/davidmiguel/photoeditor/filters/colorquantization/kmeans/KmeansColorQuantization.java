package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import com.davidmiguel.photoeditor.filters.Filter;

import javafx.scene.image.Image;

public class KmeansColorQuantization implements Filter {

	@Override
	public Image apply(Image input) {
		// Create dataset
		DataSet ds = new DataSet(input);

		return null;
	}
}