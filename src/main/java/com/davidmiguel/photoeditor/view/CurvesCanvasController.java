package com.davidmiguel.photoeditor.view;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.function.CurvesFilter;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CurvesCanvasController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;

	@FXML
	private Canvas canvasLayout;
	@FXML
	private Canvas canvasCurves;

	private GraphicsContext gcLayout;
	private GraphicsContext gcCurves;

	private Map<Integer, Integer> points; // y = f(x)

	@FXML
	private void initialize() {
		logger.info("initialize() called");
		// Layout
		gcCurves = canvasCurves.getGraphicsContext2D();
		gcLayout = canvasLayout.getGraphicsContext2D();
		drawLayout();
		// Points
		points = new TreeMap<>();
		points.put(0, 0);
		points.put(255, 255);
		handlePoints();
	}

	@FXML
	private void handleApply() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new CurvesFilter(points);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
		reset();
	}

	@FXML
	private void handleReset() {
		reset();
	}
	
	@FXML
	private void handleBrightness() {
		reset();
		points.put(0, 20);
		points.put(235, 255);
		drawCurves();
	}
	
	@FXML
	private void handleContrast() {
		reset();
		points.put(50, 0);
		points.put(205, 255);
		drawCurves();
	}
	
	@FXML
	private void handleInversion() {
		reset();
		points.put(0, 255);
		points.put(255, 0);
		drawCurves();
	}

	/**
	 * Draw a square and a diagonal line (default function).
	 */
	private void drawLayout() {
		gcLayout.setStroke(Color.GRAY);
		gcLayout.setLineWidth(1);
		gcLayout.strokeLine(0.5, 0.5, 255.5, 0.5);
		gcLayout.strokeLine(255.5, 0.5, 255.5, 255.5);
		gcLayout.strokeLine(255.5, 255.5, 0.5, 255.5);
		gcLayout.strokeLine(0.5, 255.5, 0.5, 0.5);
		gcLayout.strokeLine(0.5, 255.5, 255.5, 0.5);
	}

	/**
	 * Add listener to user's cliks to draw the points and curves.
	 */
	private void handlePoints() {
		canvasCurves.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						points.put((int) e.getX(), 255 - (int) e.getY());
						drawCurves();
					}
				});
	}

	/**
	 * Draw points and lines joining them.
	 */
	private void drawCurves() {
		// Clear canvas
		gcCurves.clearRect(0, 0, canvasCurves.getWidth(),
				canvasCurves.getHeight());
		// Draw lines
		gcCurves.setStroke(Color.GREEN);
		gcCurves.setLineWidth(2);
		Iterator<Integer> it = points.keySet().iterator();
		int p1, p2;
		p1 = it.next();
		while (it.hasNext()) {
			p2 = it.next();
			gcCurves.strokeLine(p1, 255 - points.get(p1), p2,
					255 - points.get(p2));
			p1 = p2;
		}
		// Draw points
		gcCurves.setFill(Color.BLUE);
		for (int p : points.keySet()) {
			gcCurves.fillOval(p - 3, 255 - points.get(p) - 3, 7, 7);
		}
	}

	/**
	 * Reset points and canvas.
	 */
	private void reset() {
		// Clear points
		points.clear();
		points.put(0, 0);
		points.put(255, 255);
		// Clear canvas
		gcCurves.clearRect(0, 0, canvasCurves.getWidth(),
				canvasCurves.getHeight());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}