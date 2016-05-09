package com.davidmiguel.photoeditor.drawing;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Boundary Fill algorithms. Queue-based implementation.
 * https://en.wikipedia.org/wiki/Flood_fill
 */
public class FillingAlgorithm {
	/**
	 * 4-way boundary fill algorith
	 */
	public static void floodFill4(GraphicsContext gc, WritableImage img, int x, int y, Color fillColor,
			Color boundaryColor) {
		int width = (int) gc.getCanvas().getWidth();
		int height = (int) gc.getCanvas().getHeight();
		boolean[][] painted = new boolean[width][height];

		Queue<Integer> queue = new LinkedList<>();
		queue.add(y);
		queue.add(x);

		while (!queue.isEmpty()) {
			int y1 = queue.remove();
			int x1 = queue.remove();

			if (x1 >= 0 && x1 < width && y1 >= 0 && y1 < height) {
				Color px = img.getPixelReader().getColor(x1, y1);
				if (!painted[x1][y1] && !px.equals(boundaryColor)) {
					DrawingUtils.putPixel(gc, x1, y1, fillColor, 1);
					img.getPixelWriter().setColor(x1, y1, fillColor);
					painted[x1][y1] = true;
					queue.add(y1); queue.add(x1 + 1);
					queue.add(y1); queue.add(x1 - 1);
					queue.add(y1 + 1); queue.add(x1);
					queue.add(y1 - 1); queue.add(x1);
				}
			}
		}
	}
	
	/**
	 * 8-way boundary fill algorith
	 */
	public static void floodFill8(GraphicsContext gc, WritableImage img, int x, int y, Color backgroundColor,
			Color borderColor) {
		int width = (int) gc.getCanvas().getWidth();
		int height = (int) gc.getCanvas().getHeight();
		boolean[][] painted = new boolean[width][height];

		Queue<Integer> queue = new LinkedList<>();
		queue.add(y);
		queue.add(x);

		while (!queue.isEmpty()) {
			int y1 = queue.remove();
			int x1 = queue.remove();

			if (x1 >= 0 && x1 < width && y1 >= 0 && y1 < height) {
				Color px = img.getPixelReader().getColor(x1, y1);
				if (!painted[x1][y1] && !px.equals(borderColor)) {
					DrawingUtils.putPixel(gc, x1, y1, backgroundColor, 1);
					img.getPixelWriter().setColor(x1, y1, backgroundColor);
					painted[x1][y1] = true;
					queue.add(y1); queue.add(x1 + 1);
					queue.add(y1); queue.add(x1 - 1);
					queue.add(y1 + 1); queue.add(x1);
					queue.add(y1 - 1); queue.add(x1);
					queue.add(y1 + 1); queue.add(x1 + 1);
					queue.add(y1 + 1); queue.add(x1 - 1);
					queue.add(y1 - 1); queue.add(x1 + 1);
					queue.add(y1 - 1); queue.add(x1 - 1);
				}
			}
		}
	}
}