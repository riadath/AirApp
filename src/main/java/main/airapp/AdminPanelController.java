package main.airapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminPanelController extends Controller {

//    @FXML
//    private TableColumn<?, ?> adminFlightsDepartureDate;
//    @FXML
//    private TableColumn<?, ?> adminFlightsDepartureTime;
//    @FXML
//    private TableColumn<?, ?> adminFlightsAirplaneCode;
//    @FXML
//    private TableColumn<?, ?> adminFlightsSeatAvailable;
//    @FXML
//    private TableColumn<?, ?> adminFlightsDestination;
//    @FXML
//    private TableColumn<?, ?> adminFlightsId;
//    @FXML
//    private TableColumn<?, ?> adminFlightsName;
//    @FXML
//    private TableColumn<?, ?> adminFlightsSource;
//    @FXML
//    private TableView<FlightInfo> adminFlightsTable;
//    @FXML
//    private ListView<String> navbarList;

    @FXML
    private BorderPane infoPane;

    public void loadFlights() throws IOException {

        FXMLLoader pageBottom = new FXMLLoader(getClass().getResource("flight-table.fxml"));
        infoPane.setCenter(pageBottom.load());
        FlightTableController pageBottomController = pageBottom.getController();
        pageBottomController.getFlight();
    }

    public void loadAirplanes() throws IOException {

        FXMLLoader pageBottom = new FXMLLoader(getClass().getResource("airplane-table.fxml"));
        infoPane.setCenter((pageBottom.load()));
        AirplaneTableController pageBottomController = pageBottom.getController();
        pageBottomController.getAirplane();

    }

    public void switchToBookingPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("booking-form.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCheckinPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("checkin-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
