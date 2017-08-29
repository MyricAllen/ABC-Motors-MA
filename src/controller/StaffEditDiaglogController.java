package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Staff;

public class StaffEditDiaglogController {

	@FXML
	private TextField phoneNoField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField addressField;

	private Stage dialogStage;
	private boolean okClicked = false;

	private Staff Staff;

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
	 * Sets the staff member to be edited in the dialog.
	 * 
	 * @param staff
	 */
	public void setStaff(final Staff Staff) {
		this.Staff = Staff;

		phoneNoField.setText(Staff.getphoneNo());
		emailField.setText(Staff.getemail());
		addressField.setText(Staff.getaddress());
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
			Staff.setphoneNo(phoneNoField.getText());
			Staff.setemail(emailField.getText());
			Staff.setaddress(addressField.getText());

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

		if (phoneNoField.getText() == null || phoneNoField.getText().length() == 0) {
			errorMessage += "No valid Phone Number!\n";
		}
		if (emailField.getText() == null || emailField.getText().length() == 0) {
			errorMessage += "No valid Email!\n";
		}

		if (addressField.getText() == null || addressField.getText().length() == 0) {
			errorMessage += "No valid Address!\n";
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