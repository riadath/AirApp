package main.airapp;

import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.airapp.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class FlightScheduleController extends Controller {

    @FXML
    private TableView<FlightInfo> FlightScheduler;

    @FXML
    private TableColumn<FlightInfo, LocalDate> DepartureDate;

    @FXML
    private TableColumn<FlightInfo, LocalTime> DepartureTime;

    @FXML
    private TableColumn<FlightInfo, String> Destination;

    @FXML
    private TableColumn<FlightInfo, Integer> FlightID;

    @FXML
    private TableColumn<FlightInfo, String> FlightName;

    @FXML
    private AnchorPane FlightTable;

    @FXML
    private TableColumn<FlightInfo, Integer> No_Of_Seats;

    @FXML
    private TableColumn<FlightInfo, String> Source;

    ObservableList<FlightInfo> list = FXCollections.observableArrayList(
            new FlightInfo(1,"Etihad Airways",69,"Bangladesh","London",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) ),
            new FlightInfo(1,"Emirates",100,"Australia","Canada",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) ),
            new FlightInfo(1,"GMG",50,"Singapore","Malaysia",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) )
    );

    public void initialize() {
        FlightID.setCellValueFactory(new PropertyValueFactory<>("id"));
        FlightName.setCellValueFactory( new PropertyValueFactory<>("flightName"));
        No_Of_Seats.setCellValueFactory( new PropertyValueFactory<>("noOfSeats"));
        Source.setCellValueFactory( new PropertyValueFactory<>("source"));
        Destination.setCellValueFactory( new PropertyValueFactory<>("destination"));
        DepartureDate.setCellValueFactory( new PropertyValueFactory<>("departureDate"));
        DepartureTime.setCellValueFactory( new PropertyValueFactory<>("departureTime"));

        FlightScheduler.setItems(( list ));
    }

    public void switchToUserMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-menu-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
