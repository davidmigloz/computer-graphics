package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	@Override
	public boolean equals(Object other) {
		for (int i = 0; i < values.length; i++) {
			if (round(values[i], 3) != round(((Point) other).getValue(i), 3)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		// Construct the 32bit integer representation of this color
		int red = (int) Math.round(values[0] * 255.0);
		int green = (int) Math.round(values[1] * 255.0);
		int blue = (int) Math.round(values[2] * 255.0);
		int alpha = 255;
		int i = red;
		i = i << 8;
		i = i | green;
		i = i << 8;
		i = i | blue;
		i = i << 8;
		i = i | alpha;
		return i;
	}

	/**
	 * Rounds a decimal number to a specific number of decimal places using the
	 * mode half down.
	 * 
	 * @param number
	 *            number to round
	 * @param nDecimals
	 *            specified number of decimal places
	 * @return rounded number
	 */
	private double round(double number, int nDecimals) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(nDecimals, RoundingMode.HALF_DOWN);
		return bd.doubleValue();
	}

	@Override
	public String toString() {
		return "#P:" + Arrays.toString(values);
	}
}