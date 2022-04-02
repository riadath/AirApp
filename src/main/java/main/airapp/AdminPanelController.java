package main.airapp;

import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminPanelController extends Controller {

    @FXML
    private TableColumn<?, ?> adminFlightsDepartureDate;

    @FXML
    private TableColumn<?, ?> adminFlightsDepartureTime;

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

    //TODO: do some stuff noki

    ObservableList<FlightInfo> listFlights = FXCollections.observableArrayList(
            new FlightInfo(1,"Etihad Airways","Bangladesh","London", LocalDate.of(2022,2,19), LocalTime.of(10,43,12) ),
            new FlightInfo(2,"Emirates","Australia","Canada",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) ),
            new FlightInfo(3,"GMG","Singapore","Malaysia",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) )
    );

    ObservableList<FlightInfo> listAirplanes = FXCollections.observableArrayList(
            new FlightInfo(1,"Etihad Airways" ),
            new FlightInfo(2,"Emirates" ),
            new FlightInfo(3,"GMG" )
    );



    public void loadFlights(){
        adminFlightsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminFlightsName.setCellValueFactory( new PropertyValueFactory<>("FlightName"));
        adminFlightsSource.setCellValueFactory( new PropertyValueFactory<>("Source"));
        adminFlightsDestination.setCellValueFactory( new PropertyValueFactory<>("Destination"));
        adminFlightsDepartureDate.setCellValueFactory( new PropertyValueFactory<>("DepartureDate"));
        adminFlightsDepartureTime.setCellValueFactory( new PropertyValueFactory<>("DepartureTime"));

        adminFlightsSource.setVisible(true);
        adminFlightsDestination.setVisible(true);
        adminFlightsDepartureDate.setVisible(true);
        adminFlightsDepartureTime.setVisible(true);

        adminFlightsTable.setItems(( listFlights ));
    }
    public void loadAirplanes(){
        adminFlightsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminFlightsName.setCellValueFactory( new PropertyValueFactory<>("FlightName"));

        adminFlightsSource.setVisible(false);
        adminFlightsDestination.setVisible(false);
        adminFlightsDepartureDate.setVisible(false);
        adminFlightsDepartureTime.setVisible(false);

        adminFlightsTable.setItems(( listAirplanes ));

    }

}
