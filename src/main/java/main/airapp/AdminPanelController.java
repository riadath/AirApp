package main.airapp;

import database.repository.AirplaneRepository;
import database.repository.FlightRepository;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class AdminPanelController extends Controller {

    @FXML
    private TableColumn<?, ?> adminFlightsDepartureDate;
    @FXML
    private TableColumn<?, ?> adminFlightsDepartureTime;
    @FXML
    private TableColumn<?, ?> adminFlightsAirplaneCode;
    @FXML
    private TableColumn<?, ?> adminFlightsSeatAvailable;
    @FXML
    private TableColumn<?, ?> adminFlightsDestination;
    @FXML
    private TableColumn<?, ?> adminFlightsId;
    @FXML
    private TableColumn<?, ?> adminFlightsName;
    @FXML
    private TableColumn<?, ?> adminFlightsSource;
    @FXML
    private TableView<FlightInfo> adminFlightsTable;
    @FXML
    private ListView<String> navbarList;

    public void loadFlights() {
        adminFlightsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminFlightsName.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        adminFlightsSeatAvailable.setCellValueFactory(new PropertyValueFactory<>("noOfSeats"));
        adminFlightsSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        adminFlightsDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        adminFlightsDepartureDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        adminFlightsDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));

        adminFlightsSource.setVisible(true);
        adminFlightsDestination.setVisible(true);
        adminFlightsDepartureDate.setVisible(true);
        adminFlightsDepartureTime.setVisible(true);

        adminFlightsAirplaneCode.setVisible(false);
        adminFlightsSeatAvailable.setVisible(false);

        adminFlightsTable.setItems(new FlightRepository().flightAsObservableList());
    }

    public void loadAirplanes() {

        String[] temp_str = new AirplaneRepository().getCompanyAsStringArr();
        for (String x : temp_str) navbarList.getItems().add(x);
        navbarList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String manufacturer = navbarList.getSelectionModel().getSelectedItem();
                if (manufacturer != null) {
                    adminFlightsTable.setItems(new AirplaneRepository().airplaneAsObservable(manufacturer));
                }
            }
        });

        adminFlightsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminFlightsName.setCellValueFactory(new PropertyValueFactory<>("FlightName"));
        adminFlightsAirplaneCode.setCellValueFactory(new PropertyValueFactory<>("FlightCode"));
        adminFlightsSeatAvailable.setCellValueFactory(new PropertyValueFactory<>("NoOfSeats"));

        adminFlightsAirplaneCode.setVisible(true);

        adminFlightsSource.setVisible(false);
        adminFlightsDestination.setVisible(false);
        adminFlightsDepartureDate.setVisible(false);
        adminFlightsDepartureTime.setVisible(false);
        adminFlightsSeatAvailable.setVisible(false);

        adminFlightsTable.setItems(new AirplaneRepository().airplaneAsObservable());

    }

    public void switchToMaintenancePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("maintenance-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBookingPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("booking-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
