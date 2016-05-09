package com.davidmiguel.photoeditor.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Drawing anti-aliasing lines with Gupta-Sproull's algorithm.
 * http://elynxsdk.free.fr/ext-docs/Rasterization/Antialiasing/Gupta%20sproull%
 * 20antialiased%20lines.htm
 * https://courses.engr.illinois.edu/ece390/archive/archive-f2000/mp/mp4/anti.
 * html#algo
 * http://nokola.com/blog/post/2010/10/14/Anti-aliased-Lines-And-Optimizing-
 * Code-for-Windows-Phone-7e28093First-Look.aspx
 */
public class GuptaSproullsAlgorithm {

	public static void drawLine(GraphicsContext gc, int x1, int y1, int x2,
			int y2, Color color, int width) {
		// If it is just a point
		if(x1 == x2 && y1 == y2) {
			DrawingUtils.putPixel(gc, x1, y1, color, width);
			return;
		}

		int dx = x2 - x1;
		int dy = y2 - y1;

		int du, dv, u, x, y, ix, iy;

		// By switching to (u,v), we combine all eight octants
		int adx = dx < 0 ? -dx : dx;
		int ady = dy < 0 ? -dy : dy;
		x = x1;
		y = y1;
		if (adx > ady) {
			du = adx;
			dv = ady;
			u = x2;
			ix = dx < 0 ? -1 : 1;
			iy = dy < 0 ? -1 : 1;
		} else {
			du = ady;
			dv = adx;
			u = y2;
			ix = dx < 0 ? -1 : 1;
			iy = dy < 0 ? -1 : 1;
		}

		int uEnd = u + du;
		int d = (2 * dv) - du; // Initial value as in Bresenham's
		int incrS = 2 * dv; // Δd for straight increments
		int incrD = 2 * (dv - du); // Δd for diagonal increments
		int twovdu = 0; // Numerator of distance
		double invD = 1.0 / (2.0 * Math.sqrt(du * du + dv * dv)); // Precomputed inverse denominator
		double invD2du = 2.0 * (du * invD); // Precomputed constant

		if (adx > ady) {
			do {
				intensifyPixel(gc, x, y, twovdu * invD, color);
				intensifyPixel(gc, x, y + iy, invD2du - twovdu * invD, color);
				intensifyPixel(gc, x, y - iy, invD2du + twovdu * invD, color);

				if (d < 0) {
					// Choose straight
					twovdu = d + du;
					d += incrS;

				} else {
					// Choose diagonal
					twovdu = d - du;
					d += incrD;
					y += iy;
				}
				u++;
				x += ix;
			} while (u < uEnd);
		} else {
			do {
				intensifyPixel(gc, x, y, twovdu * invD, color);
				intensifyPixel(gc, x, y + iy, invD2du - twovdu * invD, color);
				intensifyPixel(gc, x, y - iy, invD2du + twovdu * invD, color);

				if (d < 0) {
					// Choose straight
					twovdu = d + du;
					d += incrS;

				} else {
					// Choose diagonal
					twovdu = d - du;
					d += incrD;
					x += ix;
				}
				u++;
				y += iy;
			} while (u < uEnd);
		}
	}

	/**
	 * Draw the pixel with an opacity determined by its distance.
	 */
	private static void intensifyPixel(GraphicsContext gc, int x, int y,
			double distance, Color c) {
		// Normalized inverse of distance squared
		double alpha = 1 - Math.pow((distance * 2 / 3), 2);
		Color color = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
		DrawingUtils.putPixel(gc, x, y, color, 1);
	}
}