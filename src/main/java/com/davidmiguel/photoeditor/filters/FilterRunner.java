package com.davidmiguel.photoeditor.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidmiguel.photoeditor.MainApp;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

/**
 * Run a filter in a background thread.
 */
public class FilterRunner extends Service<Image> {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private MainApp mainApp;
	private Filter filter;
	private Dialog<Void> dialog;
	private ProgressBar progressBar;

	public FilterRunner(MainApp mainApp) {
		super();
		this.mainApp = mainApp;

		// Create dialog info running filter
		dialog = new Dialog<>();
		dialog.setTitle("Running filter...");
		dialog.setHeaderText("The filter is being applied to the image...");
		dialog.setWidth(600);
		progressBar = new ProgressBar();
		dialog.getDialogPane().setContent(progressBar);

		// When the filter is running show dialog
		setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				logger.debug("Running filter...");
				dialog.show();
			}
		});

		// When finished, show image and close dialog
		setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				logger.debug("Filter finished!");
				mainApp.setImage((Image) event.getSource().getValue());
				hideDialog(dialog);
				mainApp.getFR().reset();
			}
		});
	}

	public FilterRunner setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

	@Override
	protected Task<Image> createTask() {
		Task<Image> task = new Task<Image>() {
			@Override
			protected Image call() throws Exception {
				logger.debug("Running filter");
				Image result = filter.apply(mainApp.getImage());
				return result;
			}
		};
		progressBar.progressProperty().bind(task.progressProperty());
		return task;
	}

	private void hideDialog(Dialog<?> d) {
		// for the dialog to be able to hide, we need a cancel button
		DialogPane dp = d.getDialogPane();
		dp.getButtonTypes().add(ButtonType.CANCEL);
		d.hide();
		dp.getButtonTypes().remove(ButtonType.CANCEL);
	}
}