package com.davidmiguel.photoeditor.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;
import com.davidmiguel.photoeditor.filters.Filter;
import com.davidmiguel.photoeditor.filters.convolution.PersonalizedFilter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PersonalizedFilterDialogController {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;
	private Stage dialogStage;

	@FXML
	private GridPane grid;

	@FXML
	private void initialize() {
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleOk() {
		int size = grid.getRowConstraints().size();
		logger.debug("size: " + size);
		double[][] matrix = new double[size][size];
		ObservableList<Node> childrens = grid.getChildren();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				TextField input = (TextField) childrens.get(i * size + j);
				matrix[i][j] = Double.parseDouble(input.getText());
			}
		}
		Filter filter = new PersonalizedFilter(matrix);
		Image result = filter.apply(this.mainApp.getImage());
		this.mainApp.setImage(result);

		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	@FXML
	private void handleAdd() {
		dialogStage.close();
	}
}