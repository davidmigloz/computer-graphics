package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import java.util.List;

import com.davidmiguel.photoeditor.filters.Filter;

import javafx.scene.image.Image;

public class KmeansColorQuantization implements Filter {

	private int k;

	public KmeansColorQuantization(int k) {
		this.k = k;
	}

	@Override
	public Image apply(Image input) {
		// Create dataset
		DataSet ds = new DataSet(input);
		// Run k-means
		KMeans kMeans = new KMeans(ds);
		List<Cluster> clusters = kMeans.run(k);

		return null;
	}
}