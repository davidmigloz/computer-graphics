package com.davidmiguel.photoeditor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.view.EditorController;
import com.davidmiguel.photoeditor.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private Stage primaryStage;
	private BorderPane rootLayout;
	private EditorController editorController;

	private Image image;

	public MainApp() {
		super();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.info("Program Begins");

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PhotoEditor");
		configureStage();
		configureEditor();
		this.primaryStage.show();
	}

	private void configureStage() {
		try {
			// Load root layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			// Give the controller access to the main app
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			logger.error(null, e);
			System.exit(1);
		}
	}

	public void configureEditor() {
		try {
			// Load editor layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Editor.fxml"));
			SplitPane editor = (SplitPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(editor);

			// Give the controller access to the main app.
			editorController = loader.getController();
			editorController.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadImage(File file) {
		try {
			String imageURL = file.toURI().toURL().toString();
			image = new Image(imageURL);
			editorController.updateImage(image);
		} catch (MalformedURLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText(
					"Could not load data from file:\n" + file.getPath());
			alert.showAndWait();
		}
	}

	/* ********************************************************************* */
	/* Getters / Setters */
	/* ********************************************************************* */

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	/* ********************************************************************* */
	/* Main */
	/* ********************************************************************* */

	public static void main(String[] args) {
		launch(args);
	}
}
