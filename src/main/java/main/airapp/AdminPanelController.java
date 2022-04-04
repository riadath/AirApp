package main.airapp;

import database.repository.AirplaneRepository;
import database.repository.FlightRepository;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.LocalTime;

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

    //TODO: do some stuff noki

    ObservableList<FlightInfo> listFlights = FXCollections.observableArrayList(
            new FlightInfo(1,"Etihad Airways","Bangladesh","London", LocalDate.of(2022,2,19), LocalTime.of(10,43,12) ),
            new FlightInfo(2,"Emirates","Australia","Canada",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) ),
            new FlightInfo(3,"GMG","Singapore","Malaysia",LocalDate.of(2022,2,19), LocalTime.of(10,43,12) )
    );

    ObservableList<FlightInfo> listAirplanes = FXCollections.observableArrayList(
            new FlightInfo(1,"Etihad Airways", "B001", 21),
            new FlightInfo(2,"Emirates", "B001", 21),
            new FlightInfo(3,"GMG", "B001", 21)
    );



    public void loadFlights(){
        adminFlightsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminFlightsName.setCellValueFactory( new PropertyValueFactory<>("FlightName"));
        adminFlightsSeatAvailable.setCellValueFactory(new PropertyValueFactory<>("noOfSeats"));
        adminFlightsSource.setCellValueFactory( new PropertyValueFactory<>("Source"));
        adminFlightsDestination.setCellValueFactory( new PropertyValueFactory<>("Destination"));
        adminFlightsDepartureDate.setCellValueFactory( new PropertyValueFactory<>("DepartureDate"));
        adminFlightsDepartureTime.setCellValueFactory( new PropertyValueFactory<>("DepartureTime"));

        adminFlightsSource.setVisible(true);
        adminFlightsDestination.setVisible(true);
        adminFlightsDepartureDate.setVisible(true);
        adminFlightsDepartureTime.setVisible(true);

        adminFlightsAirplaneCode.setVisible(false);
        adminFlightsSeatAvailable.setVisible(false);

        adminFlightsTable.setItems(new FlightRepository().flightAsObservableList());
    }
    public void loadAirplanes(){

        String[] t = new AirplaneRepository().getCompanyAsStringArr();
        for (String x:t) navbarList.getItems().add(x);
//        navbarList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        adminFlightsName.setCellValueFactory( new PropertyValueFactory<>("FlightName"));
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

}
