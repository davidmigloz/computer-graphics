package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.BrightnessFilter;
import com.davidmiguel.photoeditor.filters.Filter;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

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
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BrightnessFilter(10);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleBrightnessDownX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BrightnessFilter(-10);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleBrightnessUpX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BrightnessFilter(30);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleBrightnessDownX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BrightnessFilter(-30);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
}
