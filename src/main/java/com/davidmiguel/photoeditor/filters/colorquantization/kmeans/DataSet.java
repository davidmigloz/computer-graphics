package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * A dataset is a collection of data points. This class creates a dataset from
 * an image. It creates one point for each pixel.
 */
public class DataSet {
	/** Points of the data set */
	private List<Point> points;

	/**
	 * Create a new data set.
	 * 
	 * @param img
	 *            image to clusterize.
	 */
	public DataSet(Image img) {
		points = new ArrayList<Point>();
		processData(img);
	}

	/**
	 * Get points.
	 * 
	 * @return list of data set's points
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * Number of features.
	 * 
	 * @return number of features of each point
	 */
	public int nFeatures() {
		return points == null || points.size() == 0 ? 0
				: points.get(0).nFeatures();
	}

	/**
	 * Parses the data set and creates Point objetcs with the features given. It
	 * also register the missed values and calculates the mean and standard
	 * deviation for each feature.
	 * 
	 * @param img
	 *            image to clusterize
	 */
	private void processData(Image img) {
		// Reader for the image
		PixelReader pixelReader = img.getPixelReader();
		int width = (int) img.getWidth();
		int height = (int) img.getHeight();
		// Create a points (pixels)
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = pixelReader.getColor(x, y);
				double[] rgb = { color.getRed(), color.getGreen(),
						color.getBlue() };
				points.add(new Point(rgb));
			}
		}
	}
}