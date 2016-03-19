package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import java.util.Arrays;

/**
 * A point is composed of one or more attributes or features. It has an array
 * with its values of each feature.
 */
public class Point {
	/** Array with the values of the features of the point */
	private double[] values;

	/**
	 * Create a point.
	 * 
	 * @param values
	 *            values of the point
	 */
	public Point(double[] values) {
		this.values = values;
	}

	/**
	 * Get value of a feature of the point.
	 * 
	 * @param feaure
	 *            position of the feature (starting from 0)
	 * @return value of the feature
	 */
	public double getValue(int feaure) {
		return values[feaure];
	}

	/**
	 * Get the values of all the features of the point.
	 * 
	 * @return array with all the values of the features of the point
	 */
	public double[] getValues() {
		return values;
	}

	/**
	 * Set the value of a feature.
	 * 
	 * @param f
	 *            feature
	 * @param value
	 *            value of the feature
	 */
	public void setValue(int f, double value) {
		this.values[f] = value;
	}

	/**
	 * Set the values of all features.
	 * 
	 * @param values
	 *            array of features
	 */
	public void setValues(double[] values) {
		this.values = values;
	}

	/**
	 * Get number of features.
	 * 
	 * @return number of features that the point has
	 */
	public int nFeatures() {
		return values.length;
	}

	/**
	 * Calculate the Squared Euclidean Distance from the point to another point.
	 * It place progressively greater weight on objects that are farther apart.
	 * 
	 * @param target
	 *            point
	 * @return Squared Euclidean Distance
	 */
	public Double squaredEuclidianDistance(Point target) {
		Double d = 0D;
		for (int i = 0; i < this.nFeatures(); i++) {
			d += Math.pow(values[i] - target.getValue(i), 2.0);
		}
		return d;
	}

	/**
	 * Determines if two points are the same.
	 * 
	 * @param other
	 *            point to compare
	 * @return true / false
	 */
	public boolean equals(Point other) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] != other.getValue(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "#P:" + Arrays.toString(values);
	}
}