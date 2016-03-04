package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.BrightnessFilter;
import com.davidmiguel.photoeditor.filters.ContrastFilter;
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
	
	@FXML
	private void handleConstrastUpX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new ContrastFilter(1.1);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleConstrastDownX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new ContrastFilter(0.9);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleConstrastUpX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new ContrastFilter(2);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleConstrastDownX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new ContrastFilter(0.5);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
}
