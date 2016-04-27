package com.davidmiguel.photoeditor.drawing;

import javafx.scene.canvas.GraphicsContext;

/**
 * Drawing circles with Midpoint Circle Algorithm.
 * https://en.wikipedia.org/wiki/Midpoint_circle_algorithm
 * http://www.tutorialspoint.com/computer_graphics/circle_generation_algorithm.htm
 */
public class MidpointCircleAlgorithm {

	public static void drawCircle(GraphicsContext gc, final int centerX,
			final int centerY, final int radius) {
		int d = 3 - 2 * radius; // Decision parameter
		int x = 0;
		int y = radius;

		do {
			// Draw eight octants simultaneously (compute for 45-90º and mirror it)
			DrawingUtils.putPixel(gc, centerX + x, centerY + y);
			DrawingUtils.putPixel(gc, centerX + x, centerY - y);
			DrawingUtils.putPixel(gc, centerX - x, centerY + y);
			DrawingUtils.putPixel(gc, centerX - x, centerY - y);
			DrawingUtils.putPixel(gc, centerX + y, centerY + x);
			DrawingUtils.putPixel(gc, centerX + y, centerY - x);
			DrawingUtils.putPixel(gc, centerX - y, centerY + x);
			DrawingUtils.putPixel(gc, centerX - y, centerY - x);
			if (d < 0) {
				// (X+1, Y) is chosen as next pixel
				d += 4 * x + 6;
			} else {
				// (X+1, Y-1) is chosen as the next pixel
				d += 4 * (x - y) + 10;
				y--;
			}
			x++;
		} while (x <= y);
	}
}