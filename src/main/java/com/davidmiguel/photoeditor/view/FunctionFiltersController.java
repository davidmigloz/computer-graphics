package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.function.BrightnessFilter;
import com.davidmiguel.photoeditor.filters.function.ContrastFilter;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.function.GammaFilter;
import com.davidmiguel.photoeditor.filters.function.GrayscaleFilter;
import com.davidmiguel.photoeditor.filters.function.InversionFilter;

import javafx.fxml.FXML;

public class FunctionFiltersController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;

	@FXML
	private void initialize() {
		logger.info("initialize() called");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleBrightnessUpX1() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new BrightnessFilter(10);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleBrightnessDownX1() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new BrightnessFilter(-10);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleBrightnessUpX2() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new BrightnessFilter(30);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleBrightnessDownX2() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new BrightnessFilter(-30);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleConstrastUpX1() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new ContrastFilter(1.1);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleConstrastDownX1() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new ContrastFilter(0.9);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleConstrastUpX2() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new ContrastFilter(2);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleConstrastDownX2() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new ContrastFilter(0.5);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleInversion() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new InversionFilter();
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleGammaUpX1() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new GammaFilter(1.03);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleGammaDownX1() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new GammaFilter(0.97);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleGammaUpX2() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new GammaFilter(1.2);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}

	@FXML
	private void handleGammaDownX2() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new GammaFilter(0.8);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleGrayscale() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		Filter filter = new GrayscaleFilter();
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
}