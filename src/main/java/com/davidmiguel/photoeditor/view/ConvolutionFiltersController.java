package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.convolution.BlurFilter;
import com.davidmiguel.photoeditor.filters.convolution.EdgeFilter;
import com.davidmiguel.photoeditor.filters.convolution.GaussianFilter;
import com.davidmiguel.photoeditor.filters.convolution.SharpenFilter;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

public class ConvolutionFiltersController {

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
	private void handleBlurX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BlurFilter(3);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleBlurX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BlurFilter(5);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleGaussianX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new GaussianFilter(3);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleGaussianX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new GaussianFilter(5);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleSharpenX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new SharpenFilter(3);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleSharpenX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new SharpenFilter(5);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleEdgeV() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EdgeFilter('V');
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleEdgeH() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EdgeFilter('H');
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
	
	@FXML
	private void handleEdgeD() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EdgeFilter('D');
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);
	}
}
