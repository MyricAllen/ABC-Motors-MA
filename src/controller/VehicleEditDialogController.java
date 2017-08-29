package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Vehicle;
import view.CurrencyFormatter;

public class VehicleEditDialogController {

	@FXML
	private TextField categoryField;

	@FXML
	private TextField VINField;

	@FXML
	private TextField costPriceField;

	@FXML
	private TextField makeField;

	private Stage dialogStage;
	private Vehicle Vehicle;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(final Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the Vehicle to be edited in the dialog.
	 * 
	 * @param Vehicle
	 */
	public void setVehicle(final Vehicle Vehicle) {
		this.Vehicle = Vehicle;

		makeField.setText(Vehicle.getmake());
		VINField.setText(Vehicle.getVin());
		costPriceField.setText("£" + Vehicle.getCostPrice());
		costPriceField.setTextFormatter(new CurrencyFormatter());
		categoryField.setText(Vehicle.getCategoryString());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			Vehicle.setmake(makeField.getText());
			Vehicle.setVin(VINField.getText());
			// Vehicle.setcategory(categoryLabel.getText());// Will have to use
			// checkboxes
			Double.valueOf(costPriceField.getText());;

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (makeField.getText() == null || makeField.getText().length() == 0) {
			errorMessage += "No valid Make!\n";
		}
		if (VINField.getText() == null || VINField.getText().length() == 0) {
			errorMessage += "No valid VIN!\n";
		}

		if (costPriceField.getText() == null || costPriceField.getText().length() == 0) {
			errorMessage += "No valid Cost price!\n";
		}

		if (categoryField.getText() == null || categoryField.getText().length() == 0) {
			errorMessage += "No valid Category!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			final Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}

		/**
		 * Called when the user clicks the edit button. Opens a dialog to edit
		 * details for the selected trip.
		 */

	}
}