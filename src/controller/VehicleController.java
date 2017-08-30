package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Vehicle;

public class VehicleController {
	@FXML
	private TableView<Vehicle> vehicleTable;
	@FXML
	private TableColumn<Vehicle, String> regNoColumn;
	@FXML
	private TableColumn<Vehicle, String> modelColumn;

	@FXML
	private Label categoryLabel;

	@FXML
	private Label VINLabel;

	@FXML
	private Label costPriceLabel;

	@FXML
	private Label makeLabel;
	
	private MainController mainController;
	
	public VehicleController() {
    }
	
	
	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the Vehicle table with the two columns.
    	regNoColumn.setCellValueFactory(cellData -> cellData.getValue().getRegNoProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().getmodelProperty());
        
        // Clear Vehicle details.
        showVehicleDetails(null);

        // Listen for selection changes and show the Vehicle details when changed.
        vehicleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVehicleDetails(newValue));
    }
	
	 /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
	public void setMainController(final MainController mainController) {
        this.mainController = mainController;

        // Add observable list data to the table
        vehicleTable.setItems(mainController.getVehicleData());
	}
	
	 /**
     * Fills all text fields to show details about the vehicle.
     * If the specified vehicle is null, all text fields are cleared.
     * 
     * @param Vehicle the Vehicle or null
     */
	private void showVehicleDetails(final Vehicle Vehicle) {
        if (Vehicle != null) {
            // Fill the labels with info from the vehicle object.
            categoryLabel.setText(Vehicle.getCategoryString());
            VINLabel.setText(Vehicle.getVin());
            costPriceLabel.setText("£" + String.valueOf( Vehicle.getCostPrice()));
            makeLabel.setText(Vehicle.getmake());
        } else {
            // vehicle is null, remove all the text.
            categoryLabel.setText("");
            VINLabel.setText("");
            costPriceLabel.setText("");
            makeLabel.setText("");
        }
    }
	
	/**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteVehicle() {
        final int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            vehicleTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            final Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainController.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Vehicle Selected");
            alert.setContentText("Please choose a Vehicle in the table.");
            
            alert.showAndWait();
        }

    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Vehicle.
     */
    @FXML
    private void handleNewVehicle() {
        final Vehicle tempVehicle = new Vehicle();
        final boolean okClicked = mainController.showVehicleEditDialog(tempVehicle);
        if (okClicked) {
            mainController.getVehicleData().add(tempVehicle);
        }
    }
    
    @FXML
    private void handleEditVehicle() {
        final Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            final boolean okClicked = mainController.showVehicleEditDialog(selectedVehicle);
            if (okClicked) {
                showVehicleDetails(selectedVehicle);
            }

        } else {
            // Nothing selected.
            final Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainController.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No vehicle Selected");
            alert.setContentText("Please select a vehicle in the table.");
            
            alert.showAndWait();
        }
    }
}