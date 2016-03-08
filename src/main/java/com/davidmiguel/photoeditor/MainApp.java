package com.davidmiguel.photoeditor;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.image.Histogram;
import com.davidmiguel.photoeditor.view.ConvolutionFiltersController;
import com.davidmiguel.photoeditor.view.CurvesCanvasController;
import com.davidmiguel.photoeditor.view.EditorController;
import com.davidmiguel.photoeditor.view.FunctionFiltersController;
import com.davidmiguel.photoeditor.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private Stage primaryStage;
	private BorderPane rootLayout;
	private EditorController editorController;

	private File file;
	private Image original;
	private Image image;
	private Image imagePrev;
	private Histogram histogram;

	public MainApp() {
		super();
	}

	/* ********************************************************************* */
	/* Views inflation */
	/* ********************************************************************* */

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.info("Program Begins");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PhotoEditor");
		configureStage();
		configureEditor();
		configureFunctionFilters();
		configureConvolutionFilters();
		configureCurvesCanvas();
		this.primaryStage.show();
	}

	/**
	 * Inflate root layout.
	 */
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

	/**
	 * Inflate editor layout.
	 */
	private void configureEditor() {
		try {
			// Load editor layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Editor.fxml"));
			SplitPane editorLayout = (SplitPane) loader.load();
			// Set in root layout
			rootLayout.setCenter(editorLayout);
			// Give the controller access to the main app
			editorController = loader.getController();
			editorController.setMainApp(this);
		} catch (IOException e) {
			logger.error(null, e);
			System.exit(1);
		}
	}

	/**
	 * Inflate function filters controls.
	 */
	private void configureFunctionFilters() {
		try {
			// Load function filters layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					MainApp.class.getResource("view/FunctionFilters.fxml"));
			AnchorPane functionFilters = (AnchorPane) loader.load();
			// Set in editor layout
			for (Tab tab : editorController.getFiltersTabs().getTabs()) {
				if (tab.getId().equals("function")) {
					tab.setContent(functionFilters);
				}
			}
			// Give the controller access to the main app
			FunctionFiltersController functionFiltersController = loader
					.getController();
			functionFiltersController.setMainApp(this);
		} catch (IOException e) {
			logger.error(null, e);
			System.exit(1);
		}
	}

	/**
	 * Inflate convolution filters controls.
	 */
	private void configureConvolutionFilters() {
		try {
			// Load function filters layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					MainApp.class.getResource("view/ConvolutionFilters.fxml"));
			AnchorPane convolutionFilters = (AnchorPane) loader.load();
			// Set in editor layout
			for (Tab tab : editorController.getFiltersTabs().getTabs()) {
				if (tab.getId().equals("convolution")) {
					tab.setContent(convolutionFilters);
				}
			}
			// Give the controller access to the main app
			ConvolutionFiltersController convolutionFiltersController = loader
					.getController();
			convolutionFiltersController.setMainApp(this);
		} catch (IOException e) {
			logger.error(null, e);
			System.exit(1);
		}
	}

	/**
	 * Inflate curves canvas.
	 */
	private void configureCurvesCanvas() {
		try {
			// Load function filters layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					MainApp.class.getResource("view/CurvesCanvas.fxml"));
			AnchorPane curvesCanvas = (AnchorPane) loader.load();
			// Set in editor layout
			editorController.getCurvesCanvasBox().getChildren()
					.add(curvesCanvas);

			// Give the controller access to the main app
			CurvesCanvasController curvesCanvasController = loader
					.getController();
			curvesCanvasController.setMainApp(this);
		} catch (IOException e) {
			logger.error(null, e);
			System.exit(1);
		}
	}

	/* ********************************************************************* */
	/* Getters / Setters */
	/* ********************************************************************* */

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public File getFile() {
		return file;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.imagePrev = this.image;
		this.image = image;
		this.histogram = new Histogram(image);
		this.editorController.updateImage(image);
		this.editorController.drawHistogram(histogram);
	}

	public Image getImagePrev() {
		return imagePrev;
	}

	public void setOriginal(Image original) {
		this.original = original;
	}

	public Image getOriginal() {
		return original;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/* ********************************************************************* */
	/* Main */
	/* ********************************************************************* */

	public static void main(String[] args) {
		launch(args);
	}
}