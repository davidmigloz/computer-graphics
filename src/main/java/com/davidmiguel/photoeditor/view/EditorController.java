package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EditorController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private MainApp mainApp;
	
	@FXML
	private ImageView imageView;
	@FXML
	private TabPane filtersTabs;
	@FXML
	private AnchorPane curvesCanvasBox;

	@FXML
	private void initialize() {
		logger.info("initialize() called");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void updateImage(Image image) {
		imageView.setImage(this.mainApp.getImage());
	}

	public TabPane getFiltersTabs() {
		return filtersTabs;
	}

	public AnchorPane getCurvesCanvasBox() {
		return curvesCanvasBox;
	}
}