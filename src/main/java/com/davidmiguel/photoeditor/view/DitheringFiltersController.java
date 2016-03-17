package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.dithering.RandomDitheringFilter;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

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
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
}