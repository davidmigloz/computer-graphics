package com.davidmiguel.photoeditor.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Drawing lines with Symmetric Midpoint Line Algorithm.
 * Based on Bresenham's line algorithm from
 * https://rosettacode.org/wiki/Bitmap/Bresenham's_line_algorithm#Java
 */
public class SymmetricMidpointLineAlgorithm {
	public static void drawLine(GraphicsContext gc, int x1, int y1, int x2,
			int y2, Color color, int width) {
		// If it is just a point
		if(x1 == x2 && y1 == y2) {
			DrawingUtils.putPixel(gc, x1, y1, color, width);
			return;
		}
		int d = 0;
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		// Slope scaling factors to avoid floating point
		int dy2 = (dy << 1);
		int dx2 = (dx << 1);

		// Increment direction
		int ix = x1 < x2 ? 1 : -1;
		int iy = y1 < y2 ? 1 : -1;

		// First point and last point
		int xf = x1, yf = y1;
		int xl = x2, yl = y2;

		if (dy <= dx) {
			if (xf > xl) {
				// Reverse 
				xf = x2; xl = x1;
				yf = y2; yl = y1;
				ix *= -1; iy *= -1;
			}
			do {
				DrawingUtils.putPixel(gc, xf, yf, color, width);
				DrawingUtils.putPixel(gc, xl, yl, color, width);
				xf += ix;
				xl -= ix;
				d += dy2;
				if (d > dx) {
					yf += iy;
					yl -= iy;
					d -= dx2;
				}
			} while (xf <= xl);
		} else {
			if (yf > yl) {
				// Reverse 
				xf = x2; xl = x1;
				yf = y2; yl = y1;
				ix *= -1; iy *= -1;
			}
			do {
				DrawingUtils.putPixel(gc, xf, yf, color, width);
				DrawingUtils.putPixel(gc, xl, yl, color, width);
				yf += iy;
				yl -= iy;
				d += dx2;
				if (d > dy) {
					xf += ix;
					xl -= ix;
					d -= dy2;
				}
			} while (yf <= yl);
		}
	}
}