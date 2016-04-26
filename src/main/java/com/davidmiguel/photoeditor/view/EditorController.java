package com.davidmiguel.photoeditor.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.image.Histogram;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class EditorController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private static final double OPACITY = 0.8;

	private MainApp mainApp;

	@FXML
	private ImageView imageView;
	@FXML
	private Canvas drawingCanvas;
	@FXML
	private TabPane filtersTabs;
	@FXML
	private AnchorPane curvesCanvasBox;
	@FXML
	private AnchorPane drawingBox;
	@FXML
	private Canvas histogramCanvas;

	private GraphicsContext gc;

	@FXML
	private void initialize() {
		logger.info("initialize() called");
		gc = histogramCanvas.getGraphicsContext2D();
		gc.setGlobalAlpha(OPACITY);
		gc.setLineWidth(1);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void updateImage(Image image) {
		imageView.setImage(this.mainApp.getImage());
	}

	public Canvas getDrawingCanvas() {
		return drawingCanvas;
	}

	public TabPane getFiltersTabs() {
		return filtersTabs;
	}

	public AnchorPane getCurvesCanvasBox() {
		return curvesCanvasBox;
	}
	
	public AnchorPane getDrawingBox() {
		return drawingBox;
	}

	public void drawHistogram(Histogram h) {
		clearCanvas();
		drawCurve(h.getRed(), Color.RED);
		drawCurve(h.getGreen(), Color.GREEN);
		drawCurve(h.getBlue(), Color.BLUE);
	}

	/**
	 * Draw lines from the bottom of the histogram to the height of each given
	 * point with the given color.
	 */
	public void drawCurve(List<Integer> points, Color color) {
		gc.setStroke(color);
		for (int i = 0; i < points.size(); i++) {
			gc.strokeLine(i + 0.5, 100.5, i + 0.5, 100.5 - points.get(i));
		}
	}

	private void clearCanvas() {
		gc.setGlobalAlpha(1);
		gc.clearRect(0, 0, histogramCanvas.getWidth(),
				histogramCanvas.getHeight());
		gc.setGlobalAlpha(OPACITY);
	}
}