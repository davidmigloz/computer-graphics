package com.davidmiguel.photoeditor.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingUtils {
	static void putPixel(GraphicsContext gc, int x, int y, Color c, int width) {
		for(int i = x; i < x + width; i++) {
			for(int j = y; j < y + width; j++) {
				gc.getPixelWriter().setColor(i, j, c);
			}
		}		
	}
}