package controller;

import java.io.IOException;
import java.util.EnumSet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Category;
import model.Staff;
import model.Vehicle;

public class MainController extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private final ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
	private final ObservableList<Staff> staffData = FXCollections.observableArrayList();

	public MainController() {
		vehicleData.add(new Vehicle("6253855627", "HF17 VLA", 26000, EnumSet.of(Category.CONVERTIBLE, Category.VAN), "DS", "DS3"));
		staffData.add(new Staff("John Smith", "07456990237", "JohnSmith@Gmail.com", "276 Stiby Road Yeovil BA234UQ"));
	}
	
		

	@Override
	public void start(final Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ABC Motors App");

		initRootLayout();

		showVehicleOverview();
		showStaffOverview();

	}

	private void showVehicleOverview() {
		try {
			// Load vehicle overview.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("../view/VehicleOverview.fxml"));
			final AnchorPane vehicleOverview = (AnchorPane) loader.load();

			// Set vehicle overview into the first root tab
			final TabPane tabPane = (TabPane) rootLayout.getCenter();
			final ObservableList<Tab> tabs = tabPane.getTabs();
			tabs.get(0).setContent(vehicleOverview);

			// Give the controller access to the main app.
			final VehicleController controller = loader.getController();
			controller.setMainController(this);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the root layout.
	 */
	private void initRootLayout() {
		try {
			// Load root layout from fxml file.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			final Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Vehicle> getVehicleData() {
		return this.vehicleData;
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public boolean showVehicleEditDialog(Vehicle tempVehicle) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("../view/VehicleEditDialog.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			final Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Vehicle");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			final Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			final VehicleEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setVehicle(tempVehicle);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (final IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void showStaffOverview() {
		try {
			// Load staff overview.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("../view/StaffOverview.fxml"));
			final AnchorPane staffOverview = (AnchorPane) loader.load();

			// Set staff overview into the first root tab
			final TabPane tabPane = (TabPane) rootLayout.getCenter();
			final ObservableList<Tab> tabs = tabPane.getTabs();
			tabs.get(1).setContent(staffOverview);

			// Give the controller access to the main app.
			final StaffController controller = loader.getController();
			controller.setMainController(this);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showStaffEditDialog(Staff selectedStaff) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("../view/StaffEditDialog.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			final Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Staff");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			final Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			final StaffEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStaff(selectedStaff);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (final IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ObservableList<Staff> getStaffData() {
		return this.staffData;
	}
}
