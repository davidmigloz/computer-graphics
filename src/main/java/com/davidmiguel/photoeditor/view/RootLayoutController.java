package com.davidmiguel.photoeditor.view;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		fileChooser.setTitle("Open Image");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JPG files (*.jpg, *.jpeg)",
						"*.jpg", "*.jpeg"),
				new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		if (file != null) {
			// Load image
			mainApp.loadImage(file);
		}
	}
	
	/**
	 * Save image to original file.
	 */
	@FXML
	private void handleSave() {
		if (isOpenedImage()) {
			File file = mainApp.getFile();
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".png")) {
                file = new File(file.getPath() + ".png");
            }
            mainApp.saveImage(file);
		}
	}

	/**
	 * Save image to a chosen file.
	 */
	@FXML
	private void handleSaveAs() {
		if (isOpenedImage()) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Image");
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("PNG files (*.png)",
							"*.png"));
			File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
			if (file != null) {
	            // Make sure it has the correct extension
	            if (!file.getPath().endsWith(".png")) {
	                file = new File(file.getPath() + ".png");
	            }
	            mainApp.saveImage(file);
			}
		}
	}

	/**
	 * Check if there is an opened image.
	 */
	private boolean isOpenedImage() {
		if (mainApp.getImage() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save the image");
			alert.setContentText("There is no opened image");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
