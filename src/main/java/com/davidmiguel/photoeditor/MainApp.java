package com.davidmiguel.photoeditor;

import java.io.File;
import java.io.IOException;

import com.davidmiguel.photoeditor.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	private Image image;
	
    public MainApp() {
        super();
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		 this.primaryStage = primaryStage;
		 configureStage();
		 this.primaryStage.setTitle("PhotoEditor");		 
		 this.primaryStage.show();
	}
	
	private void configureStage() {
        try {
            // Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            // Give the controller access to the main app
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public static void main(String[] args) {
        launch(args);
    }
}
