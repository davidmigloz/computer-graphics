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

public class DrawingController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;
	private Canvas canvas;
	private GraphicsContext gc;

	@FXML
	private void initialize() {
		logger.info("initialize() called");
	}
	
	private void configureCanvas() {
		canvas = mainApp.getDrawingCanvas();
		gc = canvas.getGraphicsContext2D();
		handleDrawing();
	}

	/**
	 * Add listener to user's cliks.
	 */
	private void handleDrawing() {
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						gc.setFill(Color.BLUE);
						gc.fillOval((int) e.getX(), (int) e.getY(), 7, 7);
					}
				});
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		configureCanvas();
	}
}