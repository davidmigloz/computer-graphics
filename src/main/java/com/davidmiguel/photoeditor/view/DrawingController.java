package com.davidmiguel.photoeditor.view;

import java.awt.geom.Point2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.drawing.GuptaSproullsAlgorithm;
import com.davidmiguel.photoeditor.drawing.MidpointCircleAlgorithm;
import com.davidmiguel.photoeditor.drawing.SymmetricMidpointLineAlgorithm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;

public class DrawingController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@FXML
	private RadioButton lineRadio;
	@FXML
	private RadioButton circleRadio;
	@FXML
	private RadioButton noAARadio;
	@FXML
	private RadioButton AARadio;
	@FXML
	private TextField widthInput;
	@FXML
	private ColorPicker colorPicker;

	private MainApp mainApp;
	private Canvas canvas;
	private GraphicsContext gc;
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;

	@FXML
	private void initialize() {
		logger.info("initialize() called");
	}

	private void configureCanvas() {
		canvas = mainApp.getDrawingCanvas();
		gc = canvas.getGraphicsContext2D();
		handleMouse();
		handleRadios();
	}

	/**
	 * Add listener to user's cliks.
	 */
	private void handleMouse() {
		// Get coordenates when mouse pressed
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						fromX = (int) e.getX();
						fromY = (int) e.getY();
					}
				});
		// Get coordenates when released and draw
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						toX = (int) e.getX();
						toY = (int) e.getY();
						handleDraw();
					}
				});
	}

	/**
	 * Manages the radio buttons.
	 */
	private void handleRadios() {
		lineRadio.getToggleGroup().selectedToggleProperty()
				.addListener(new ChangeListener<Toggle>() {
					@Override
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue) {
						if (circleRadio.isSelected()) {
							noAARadio.setDisable(true);
							AARadio.setDisable(true);
						} else if (lineRadio.isSelected()) {
							noAARadio.setDisable(false);
							AARadio.setDisable(false);
						}
					}
				});
	}

	/**
	 * Draw the selected shape with the selected algoritm.
	 */
	private void handleDraw() {
		// Get color
		gc.setStroke(colorPicker.getValue());
		// Get width
		gc.setLineWidth(Integer.parseInt(widthInput.getText()));
		// Get shape
		if (lineRadio.isSelected()) {
			if (noAARadio.isSelected()) {
				drawLine();
			} else if (AARadio.isSelected()) {
				drawLineAA();
			}
		} else if (circleRadio.isSelected()) {
			drawCircle();
		}
	}

	/**
	 * Line with Symmetric Midpoint Line Algorithm.
	 */
	private void drawLine() {
		SymmetricMidpointLineAlgorithm.drawLine(gc, fromX, fromY, toX, toY);
	}

	/**
	 * Line with Goupta-Sproull’s algorithm.
	 */
	private void drawLineAA() {
		GuptaSproullsAlgorithm.drawLine(gc, fromX, fromY, toX, toY,
				colorPicker.getValue());
	}

	/**
	 * Circle with Midpoint Circle Algorithm.
	 */
	private void drawCircle() {
		MidpointCircleAlgorithm.drawCircle(gc, fromX, fromY,
				(int) Point2D.distance(fromX, fromY, toX, toY));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		configureCanvas();
	}
}