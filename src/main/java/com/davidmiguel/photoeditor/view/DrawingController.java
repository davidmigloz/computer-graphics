package com.davidmiguel.photoeditor.view;

import java.awt.geom.Point2D;
import java.util.Deque;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.drawing.FillingAlgorithm;
import com.davidmiguel.photoeditor.drawing.GuptaSproullsAlgorithm;
import com.davidmiguel.photoeditor.drawing.MidpointCircleAlgorithm;
import com.davidmiguel.photoeditor.drawing.SymmetricMidpointLineAlgorithm;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class DrawingController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@FXML
	private RadioButton lineRadio;
	@FXML
	private RadioButton circleRadio;
	@FXML
	private RadioButton polygonRadio;
	@FXML
	private RadioButton fillingRadio;
	@FXML
	private TextField widthInput;
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private RadioButton noAARadio;
	@FXML
	private RadioButton AARadio;
	@FXML
	private RadioButton f4cRadio;
	@FXML
	private RadioButton f8cRadio;
	@FXML
	private ColorPicker boundaryPicker;

	private MainApp mainApp;
	private Canvas canvas;
	private GraphicsContext gc;

	private enum Option {
		LINE, CIRCLE, POLYGON, FILLING;
	}

	private Option selectedOpt = Option.LINE;
	private Deque<Double> bufferX = new LinkedList<>();
	private Deque<Double> bufferY = new LinkedList<>();

	@FXML
	private void initialize() {
		logger.info("initialize() called");
		colorPicker.setValue(Color.BLACK);
		boundaryPicker.setValue(Color.BLACK);
	}

	private void configureCanvas() {
		canvas = mainApp.getDrawingCanvas();
		gc = canvas.getGraphicsContext2D();
		handleMouse();
	}

	/**
	 * Add listener to user's cliks.
	 */
	private void handleMouse() {
		canvas.setOnMousePressed(event -> {
			switch (selectedOpt) {
			case LINE:
			case CIRCLE:
				if (bufferX.size() > 0) {
					bufferX.clear();
					bufferY.clear();
				}
				bufferX.add(event.getX());
				bufferY.add(event.getY());
				break;
			default:
				break;
			}
		});
		canvas.setOnMouseReleased(event -> {
			switch (selectedOpt) {
			case LINE:
				if (bufferX.size() == 1) {
					drawLine(bufferX.removeLast().intValue(), bufferY.removeLast().intValue(), (int) event.getX(),
							(int) event.getY());
				}
				break;
			case CIRCLE:
				if (bufferX.size() == 1) {
					drawCircle(bufferX.removeLast().intValue(), bufferY.removeLast().intValue(), (int) event.getX(),
							(int) event.getY());
				}
				break;
			case POLYGON:
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					bufferX.add(event.getX());
					bufferY.add(event.getY());
					gc.fillOval(event.getX(), event.getY(), 2, 2);
				} else if (event.getButton().equals(MouseButton.SECONDARY)) {
					double[] coordX = new double[bufferX.size()];
					double[] coordY = new double[bufferY.size()];
					int size = bufferX.size();
					for (int i = 0; i < size; i++) {
						coordX[i] = bufferX.pollFirst();
						coordY[i] = bufferY.pollFirst();
					}
					gc.strokePolygon(coordX, coordY, size);
				}
				break;
			case FILLING:
				filling((int) event.getX(), (int) event.getY());
				break;

			}
		});
	}

	/**
	 * Manages the radio buttons.
	 */
	@FXML
	private void handleRadios() {
		if (lineRadio.isSelected()) {
			selectedOpt = Option.LINE;
			widthInput.setDisable(false);
			noAARadio.setDisable(false);
			AARadio.setDisable(false);
			f4cRadio.setDisable(true);
			f8cRadio.setDisable(true);
			boundaryPicker.setDisable(true);
		} else if (circleRadio.isSelected()) {
			selectedOpt = Option.CIRCLE;
			widthInput.setDisable(false);
			noAARadio.setDisable(true);
			AARadio.setDisable(true);
			f4cRadio.setDisable(true);
			f8cRadio.setDisable(true);
			boundaryPicker.setDisable(true);
		} else if (polygonRadio.isSelected()) {
			selectedOpt = Option.POLYGON;
			widthInput.setDisable(true);
			noAARadio.setDisable(true);
			AARadio.setDisable(true);
			f4cRadio.setDisable(true);
			f8cRadio.setDisable(true);
			boundaryPicker.setDisable(true);
		} else if (fillingRadio.isSelected()) {
			selectedOpt = Option.FILLING;
			widthInput.setDisable(true);
			noAARadio.setDisable(true);
			AARadio.setDisable(true);
			f4cRadio.setDisable(false);
			f8cRadio.setDisable(false);
			boundaryPicker.setDisable(false);
		}
	}

	/**
	 * Line with Symmetric Midpoint Line Algorithm or Goupta-Sproull’s
	 * algorithm.
	 */
	private void drawLine(int fromX, int fromY, int toX, int toY) {
		if (noAARadio.isSelected()) {
			SymmetricMidpointLineAlgorithm.drawLine(gc, fromX, fromY, toX, toY, colorPicker.getValue(),
					Integer.parseInt(widthInput.getText()));
		} else if (AARadio.isSelected()) {
			GuptaSproullsAlgorithm.drawLine(gc, fromX, fromY, toX, toY, colorPicker.getValue(),
					Integer.parseInt(widthInput.getText()));
		}
	}

	/**
	 * Circle with Midpoint Circle Algorithm.
	 */
	private void drawCircle(int fromX, int fromY, int toX, int toY) {
		MidpointCircleAlgorithm.drawCircle(gc, fromX, fromY, (int) Point2D.distance(fromX, fromY, toX, toY),
				colorPicker.getValue(), Integer.parseInt(widthInput.getText()));
	}

	/**
	 * Boundary Fill Algorithms.
	 */
	private void filling(int x, int y) {
		WritableImage canvas = gc.getCanvas().snapshot(new SnapshotParameters(), null);
		if (f4cRadio.isSelected()) {
			FillingAlgorithm.floodFill4(gc, canvas, x, y, colorPicker.getValue(), boundaryPicker.getValue());
		} else if (f8cRadio.isSelected()) {
			FillingAlgorithm.floodFill8(gc, canvas, x, y, colorPicker.getValue(), boundaryPicker.getValue());
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		configureCanvas();
	}
}