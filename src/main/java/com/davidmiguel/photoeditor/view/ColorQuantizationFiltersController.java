package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.colorquantization.kmeans.KmeansColorQuantizationFilter;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ColorQuantizationFiltersController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;

	@FXML
	private TextField kInput;
	@FXML
	private RadioButton kmeansRadio;

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
		if (kmeansRadio.isSelected()) {
			filter = new KmeansColorQuantizationFilter(k);
		}
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
}