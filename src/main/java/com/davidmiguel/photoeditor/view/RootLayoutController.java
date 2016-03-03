package com.davidmiguel.photoeditor.view;

import com.davidmiguel.photoeditor.MainApp;

import javafx.fxml.FXML;

public class RootLayoutController {

	private MainApp mainApp;
		
    @FXML
    private void initialize() {
    	
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
