package com.davidmiguel.photoeditor.drawing;

import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Scan-line Polygon Fill Algorithm.
 * https://www.cs.rit.edu/~icss571/filling/index.html
 * http://rsb.info.nih.gov/ij/developer/source/ij/process/PolygonFiller.java.html
 */
public class ScanlineAlgorithm {

	// Polygon
	private int n; // number of vertices
	private int[] x; // x vertices coordinates
	private int[] y; // y vertices coordinates

	// Edges info tables
	private int edges; // number of edges
	private int[] minYs; // maximum y value of the two vertices
	private int[] maxYs; // minimum y value of the two vertices
	private double[] xMinY; // x value associated with the minimum y value
	private double[] iSlopes; // inverse slopes: 1/m where m = (y0 - y1) / (x0 - x1)

	// Global Edge Table (keep track of the edges that are still needed)
	private int activeEdges; // number of active edges
	private int[] goblalEdges;

	/**
	 * ScanlineAlgorithm.
	 * 
	 * @param x
	 *            array with x vertices coordinates
	 * @param y
	 *            array with y vertices coordinates
	 */
	public ScanlineAlgorithm(int[] x, int[] y) {
		this.x = x;
		this.y = y;
		this.n = x.length;

		xMinY = new double[n];
		minYs = new int[n];
		maxYs = new int[n];
		goblalEdges = new int[n];
		iSlopes = new double[n];

		buildEdgeTable();
	}

	/**
	 * Generates the edge info table.
	 */
	private void buildEdgeTable() {
		int i1, i2, x1, x2, y1, y2;
		edges = 0;
		for (i1 = 0; i1 < n; i1++) {
			i2 = i1 == n - 1 ? 0 : i1 + 1;
			y1 = y[i1];
			y2 = y[i2];
			x1 = x[i1];
			x2 = x[i2];
			if (y1 == y2) {
				// Ignore horizontal lines
				continue;
			}
			if (y1 > y2) {
				// Sort coords
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
				tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			double iSlope = (x2 - x1) / (double) (y2 - y1);
			minYs[edges] = y1;
			maxYs[edges] = y2;
			xMinY[edges] = y1 < y2 ? x1 : x2;
			iSlopes[edges] = iSlope;
			edges++;
		}
		activeEdges = 0;
	}

	/**
	 * Adds edges to the global edge table.
	 */
	private void activateEdges(int y) {
		for (int i = 0; i < edges; i++) {
			int edge = i;
			if (y == minYs[edge]) {
				int index = 0;
				while (index < activeEdges && xMinY[edge] > xMinY[goblalEdges[index]]) {
					index++;
				}
				for (int j = activeEdges - 1; j >= index; j--) {
					goblalEdges[j + 1] = goblalEdges[j];
				}
				goblalEdges[index] = edge;
				activeEdges++;
			}
		}
	}

	/**
	 * Removes edges from the global edge table that are no longer needed.
	 */
	private void removeInactiveEdges(int y) {
		int i = 0;
		while (i < activeEdges) {
			int index = goblalEdges[i];
			if (y < minYs[index] || y >= maxYs[index]) {
				for (int j = i; j < activeEdges - 1; j++)
					goblalEdges[j] = goblalEdges[j + 1];
				activeEdges--;
			} else {
				i++;
			}
		}
	}

	/**
	 * Updates the x coordinates in the global edges table and sorts it if
	 * necessary.
	 */
	private void updateXCoordinates() {
		int index;
		double x1 = -Double.MAX_VALUE, x2;
		boolean sorted = true;
		for (int i = 0; i < activeEdges; i++) {
			index = goblalEdges[i];
			x2 = xMinY[index] + iSlopes[index];
			xMinY[index] = x2;
			if (x2 < x1) {
				sorted = false;
			}
			x1 = x2;
		}
		if (!sorted) {
			sortActiveEdges();
		}
	}

	/**
	 * Sorts the global edges table by x coordinate using a selection sort.
	 */
	private void sortActiveEdges() {
		int min, tmp;
		for (int i = 0; i < activeEdges; i++) {
			min = i;
			for (int j = i; j < activeEdges; j++) {
				if (xMinY[goblalEdges[j]] < xMinY[goblalEdges[min]]) {
					min = j;
				}
			}
			tmp = goblalEdges[min];
			goblalEdges[min] = goblalEdges[i];
			goblalEdges[i] = tmp;
		}
	}

	/**
	 * Draw the polygon filled with given color.
	 */
	public void fill(GraphicsContext gc, Color color) {
		int minY = Arrays.stream(y).min().getAsInt();
		int maxY = Arrays.stream(y).max().getAsInt();

		int x1, x2;
		for (int y = minY; y < maxY; y++) {
			removeInactiveEdges(y);
			activateEdges(y);
			for (int i = 0; i < activeEdges; i += 2) {
				x1 = (int) (xMinY[goblalEdges[i]] + 0.5);
				x2 = (int) (xMinY[goblalEdges[i + 1]] + 0.5);
				for (int x = x1; x < x2; x++) {
					DrawingUtils.putPixel(gc, x, y, color, 1);
				}
			}
			updateXCoordinates();
		}
	}
}