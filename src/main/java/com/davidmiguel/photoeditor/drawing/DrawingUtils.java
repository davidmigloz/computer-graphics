package com.davidmiguel.photoeditor.drawing;

import javafx.scene.canvas.GraphicsContext;

public class DrawingUtils {
	static void putPixel(GraphicsContext gc, int x, int y) {
		// Center pixels
		// http://stackoverflow.com/questions/27846659/how-to-draw-an-1-pixel-line-using-javafx-canvas
		x += 0.5;
		y += 0.5;
		gc.strokeLine(x, y, x, y);
	}
}