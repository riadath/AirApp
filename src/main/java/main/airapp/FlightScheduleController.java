package main.airapp;

import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class FlightScheduleController extends Controller implements Initializable{

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FlightID.setCellValueFactory(new PropertyValueFactory<>("id"));
        FlightName.setCellValueFactory( new PropertyValueFactory<>("FlightName"));
        No_Of_Seats.setCellValueFactory( new PropertyValueFactory<>("No_Of_Seats"));
        Source.setCellValueFactory( new PropertyValueFactory<>("Source"));
        Destination.setCellValueFactory( new PropertyValueFactory<>("Destination"));
        DepartureDate.setCellValueFactory( new PropertyValueFactory<>("DepartureDate"));
        DepartureTime.setCellValueFactory( new PropertyValueFactory<>("DepartureTime"));

        FlightScheduler.setItems(( list ));
    }

    public void switchToUserMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-menu-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
