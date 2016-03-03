package com.davidmiguel.photoeditor.view;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;
		
    @FXML
    private void initialize() {
    	logger.info("initialize() called");
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
  
    /**
     * Open an image.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        // Set extension filters
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter(
                "JPG files (*.jpg, *.jpeg)", "*.jpg", "*.jpeg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter(
                "PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(jpgFilter);
        fileChooser.getExtensionFilters().add(pngFilter);
        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        // Load image
        if (file != null) {
            mainApp.loadImage(file);
        }
    }
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
