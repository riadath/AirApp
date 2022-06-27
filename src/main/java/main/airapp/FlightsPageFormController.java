package main.airapp;

import database.repository.AirplaneRepository;
import database.repository.FlightRepository;
import datamodel.fleet.AirplaneInfo;
import datamodel.fleet.CivilianFlight;
import datamodel.fleet.FlightInfo;
import datamodel.fleet.MilitaryFlight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class FlightsPageFormController extends Controller {

    @FXML
    private ComboBox<AirplaneInfo> airplanesDropDown;

    @FXML
    private DatePicker departureDate;

    private final boolean isCivilianFlight = true;

    @FXML
    private TextField departureTime;

    @FXML
    private ComboBox<String> flightType;

    @FXML
    private TextField destination;

    @FXML
    private TextField source;

    @FXML
    private Label errorlabel;

    @FXML
    public void initialize() {
        fillCombo();
    }

    public void makeFlight() {

        AirplaneInfo airplane = airplanesDropDown.getSelectionModel().getSelectedItem();

        String FlightSource = source.getText();

        FlightInfo flightInfo;

        String FlightDestination = destination.getText();

        LocalDate FlightDate = departureDate.getValue();

        LocalTime FlightTime;

        String flightTimeString = departureTime.getText();

        if (airplane == null) {
            errorlabel.setText("Select Airplane");
            return;
        } else if (FlightSource.isEmpty()) {
            errorlabel.setText("Enter Flight Source");
            return;
        } else if (FlightDestination.isEmpty()) {
            errorlabel.setText("Enter Flight Destination");
            return;
        } else if (FlightDate == null) {
            errorlabel.setText("Enter Flight Date");
            return;
        }


        if (flightTimeString.isEmpty()) {
            errorlabel.setText("Enter Flight Time");
            return;
        } else {
            try {
                if (!flightTimeString.contains(":")) throw new NumberFormatException();
                String[] arr = flightTimeString.split(":");
                FlightTime = LocalTime.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            } catch (NumberFormatException e) {
                errorlabel.setText("Time Format Parse Error. Please use HH:MM format");
                return;
            } catch (DateTimeException e) {
                errorlabel.setText("Enter a valid time in the HH:MM format");
                return;
            }
        }

        if (isCivilianFlight) {
            flightInfo = new CivilianFlight(0, FlightSource, FlightDestination, FlightDate, FlightTime, airplane.getNumber_of_seats());
        } else {
            flightInfo = new MilitaryFlight(0, FlightSource, FlightDestination, FlightDate, FlightTime, airplane.getNumber_of_seats());
        }

        flightInfo.confirmTicket();

        new FlightRepository().insert(new String[]{
                airplane.getCode(),
                FlightSource,
                FlightDestination,
                FlightDate.toEpochSecond(LocalTime.MIN, ZoneOffset.MIN) + FlightTime.toSecondOfDay() + "",
                airplane.getNumber_of_seats() + ""
        });

        errorlabel.setTextFill(Color.GREEN);
        errorlabel.setText("Flight Added Successfully!!");


    }

    private void fillCombo() {
        airplanesDropDown.setItems(new AirplaneRepository().airplaneAsObservable());

        // sets the thing to show on teh display box
        airplanesDropDown.setConverter(new StringConverter<>() {
            @Override
            public String toString(AirplaneInfo airplaneInfo) {
                if (airplaneInfo == null) return "Airplane";
                return airplaneInfo.getName();
            }

            @Override
            public AirplaneInfo fromString(String s) {
                return null;
            }
        });

        flightType.setItems(FXCollections.observableArrayList("Civilian", "Military"));

        // sets the things to show on the list
        airplanesDropDown.setCellFactory(new Callback<>() {
            @Override
            public ListCell<AirplaneInfo> call(ListView<AirplaneInfo> airplaneInfoListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(AirplaneInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName() + " " + item.getCode());
                        }
                    }
                };
            }
        });
    }


}
