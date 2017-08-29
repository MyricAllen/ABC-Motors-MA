package controller;

	import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Staff;


	public class StaffController {
		@FXML
		private TableView<Staff> staffTable;
		@FXML
		private TableColumn<Staff, String> nameColumn;
		@FXML
		private TableColumn<Staff, String> phoneNoColumn;

		@FXML
		private Label emailLabel;

		@FXML
		private Label addressLabel;
		
		private MainController mainController;
		
		public StaffController() {
	    }
		
		
		 /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	        // Initialize the teacher table with the two columns.
	    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());
	        phoneNoColumn.setCellValueFactory(cellData -> cellData.getValue().getphoneNoProperty());
	        
	        // Clear teacher details.
	        showStaffDetails(null);

	        // Listen for selection changes and show the teacher details when changed.
	        staffTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showStaffDetails(newValue));
	    }
		
		 /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
		public void setMainController(final MainController mainController) {
	        this.mainController = mainController;

	        // Add observable list data to the table
	        staffTable.setItems(mainController.getStaffData());
		}
		
		 /**
	     * Fills all text fields to show details about the Staff.
	     * If the specified Staff is null, all text fields are cleared.
	     * 
	     * @param student the student or null
	     */
		private void showStaffDetails(final Staff staff) {
	        if (staff != null) {
	            // Fill the labels with info from the Staff object.
	           emailLabel.setText(staff.getemail());
	            addressLabel.setText(staff.getaddress());
	        } else {
	            // Staff is null, remove all the text.
	            emailLabel.setText("");
	            addressLabel.setText("");
	        }
	    }
		
		/**
	     * Called when the user clicks on the delete button.
	     */
	    @FXML
	    private void handleDeleteStaff() {
	        final int selectedIndex = staffTable.getSelectionModel().getSelectedIndex();
	        if (selectedIndex >= 0) {
	            staffTable.getItems().remove(selectedIndex);
	        } else {
	            // Nothing selected.
	            final Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainController.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Staff member Selected");
	            alert.setContentText("Please choose a Staff member in the table.");
	            
	            alert.showAndWait();
	        }

	    }
	    
	    /**
	     * Called when the user clicks the new button. Opens a dialog to edit
	     * details for a new Staff member.
	     */
	    @FXML
	    private void handleNewStaff() {
	        final Staff tempStaff = new Staff();
	        final boolean okClicked = mainController.showStaffEditDialog(tempStaff);
	        if (okClicked) {
	            mainController.getStaffData().add(tempStaff);
	        }
	    }
	    
	    @FXML
	    private void handleEditStaff() {
	        final Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
	        if (selectedStaff != null) {
	            final boolean okClicked = mainController.showStaffEditDialog(selectedStaff);
	            if (okClicked) {
	                showStaffDetails(selectedStaff);
	            }

	        } else {
	            // Nothing selected.
	            final Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainController.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Staff member Selected");
	            alert.setContentText("Please select a Staff member in the table.");
	            
	            alert.showAndWait();
	        }
	    }

}
