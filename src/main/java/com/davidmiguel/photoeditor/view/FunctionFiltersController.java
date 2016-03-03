package com.davidmiguel.photoeditor.view;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

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

	}
	
	@FXML
	private void handleBrightnessDownX1() {

	}
	
	@FXML
	private void handleBrightnessUpX2() {

	}
	
	@FXML
	private void handleBrightnessDownX2() {

	}
}
