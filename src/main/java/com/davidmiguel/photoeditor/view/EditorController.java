package com.davidmiguel.photoeditor.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.davidmiguel.photoeditor.MainApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditorController {
    @FXML
    private ImageView imageView;

    private MainApp mainApp;

    @FXML
    private void initialize() {
    	
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void updateImage(Image image){
    	imageView.setImage(this.mainApp.getImage());
    }
}
