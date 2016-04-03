package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.dithering.RandomDitheringFilter;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class DitheringFiltersController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;

	@FXML
	private TextField kInput;
	@FXML
	private RadioButton randomRadio;
	@FXML
	private RadioButton averageRadio;

	@FXML
	private void initialize() {
		logger.info("initialize() called");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleApply() {
		if (this.mainApp.getImage() == null) {
			return;
		}
		// Get k
		int k = Integer.parseInt(kInput.getText());
		// Get type
		Filter filter = null;
		if (randomRadio.isSelected()) {
			filter = new RandomDitheringFilter(k);
		}
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
}