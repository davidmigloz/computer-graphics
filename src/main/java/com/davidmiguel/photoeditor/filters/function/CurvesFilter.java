package com.davidmiguel.photoeditor.filters.function;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;

/**
 * Curves function filter.
 * http://pippin.gimp.org/image_processing/chap_point.html#id2557928
 */
public class CurvesFilter extends FunctionFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public CurvesFilter(Map<Integer, Integer> points) {
		super(0);
		createLookupTable(points);
	}

	@Override
	protected void createLookupTable(double value) {
		return;
	}

	/**
	 * Create lookup table with the given points.
	 * @param points
	 */
	protected void createLookupTable(Map<Integer, Integer> points) {
		Iterator<Integer> it = points.keySet().iterator();
		int p1, p2;
		p1 = it.next();
		while (it.hasNext()) {
			p2 = it.next();
			addPixelsBetween2Points(p1, points.get(p1), p2, points.get(p2));
			p1 = p2;
		}
	}

	/**
	 * Bresenham Algorithm: determines the points of an n-dimensional raster
	 * that should be selected in order to form a close approximation to a
	 * straight line between two points.
	 * http://tech-algorithm.com/articles/drawing-line-using-bresenham-
	 * algorithm/
	 */
	private void addPixelsBetween2Points(int x1, int y1, int x2, int y2) {
		int w = x2 - x1;
		int h = y2 - y1;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (w < 0) {
			dx1 = -1;
		} else if (w > 0) {
			dx1 = 1;
		}
		if (h < 0) {
			dy1 = -1;
		} else if (h > 0) {
			dy1 = 1;
		}
		if (w < 0) {
			dx2 = -1;
		} else if (w > 0) {
			dx2 = 1;
		}
		int longest = Math.abs(w);
		int shortest = Math.abs(h);
		if (!(longest > shortest)) {
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0)
				dy2 = 1;
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++) {
			lookupTable[x1] = y1;
			numerator += shortest;
			if (!(numerator < longest)) {
				numerator -= longest;
				x1 += dx1;
				y1 += dy1;
			} else {
				x1 += dx2;
				y1 += dy2;
			}
		}
	}

	@Override
	public Image apply(Image input) {
		logger.info("Apply CurvesFilter");
		return super.apply(input);
	}
}