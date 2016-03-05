package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

	@FXML
	private void initialize() {
		logger.info("initialize() called");
		gcCurves = canvasCurves.getGraphicsContext2D();
		gcLayout = canvasLayout.getGraphicsContext2D();
		drawLayout();
		handlePoints();
	}

	private void drawLayout() {
		gcLayout.setStroke(Color.BLACK);
		gcLayout.setLineWidth(1);
		gcLayout.strokeLine(0, 0, 255, 0);
		gcLayout.strokeLine(255, 0, 255, 255);
		gcLayout.strokeLine(255, 255, 0, 255);
		gcLayout.strokeLine(0, 255, 0, 0);
		gcLayout.strokeLine(0, 255, 255, 0);
	}

	private void handlePoints() {
		canvasCurves.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						gcCurves.setFill(Color.BLUE);
						gcCurves.fillOval(e.getX(), e.getY(), 7, 7);
					}
				});
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}