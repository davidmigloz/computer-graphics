package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.convolution.BlurFilter;
import com.davidmiguel.photoeditor.filters.convolution.EdgeFilter;
import com.davidmiguel.photoeditor.filters.convolution.EmbossFilter;
import com.davidmiguel.photoeditor.filters.convolution.GaussianFilter;
import com.davidmiguel.photoeditor.filters.convolution.SharpenFilter;

import javafx.fxml.FXML;

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
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleBlurX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new BlurFilter(5);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleGaussianX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new GaussianFilter(3);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleGaussianX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new GaussianFilter(5);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleSharpenX1() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new SharpenFilter(3);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleSharpenX2() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new SharpenFilter(5);
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEdgeV() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EdgeFilter('V');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEdgeH() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EdgeFilter('H');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEdgeD() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EdgeFilter('D');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEmbossN() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EmbossFilter('N');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEmbossE() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EmbossFilter('E');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEmbossS() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EmbossFilter('S');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handleEmbossW() {
		if(this.mainApp.getImage() == null){
			return;
		}
		Filter filter = new EmbossFilter('W');
		// Apply filter
		this.mainApp.getFR().setFilter(filter).start();
	}
	
	@FXML
	private void handlePersonalizedFilter() {
		if(this.mainApp.getImage() == null){
			return;
		}
		this.mainApp.showPersonalizedFilterDialog();
	}
}