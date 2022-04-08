package main.airapp;

import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;

public class BookingPageController extends Controller {
    @FXML
    private Button bookTicketButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField destinationTextField;
    @FXML
    private ComboBox<FlightInfo> flightDropDown;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label noFlightLabel;
    @FXML
    private TextField numberOfTickets;
    @FXML
    private TextField passportNoTextField;
    @FXML
    private Button searchFlightButton;
    @FXML
    private TextField sourceTextFiled;
    @FXML
    private ComboBox<String> ticketClassDropDown;


    public void initialize() {
        ObservableList<String> ticketClassList = FXCollections.observableArrayList("First", "Business", "Economy");
        ticketClassDropDown.setItems(ticketClassList);
    }

    public void searchFlights() {
        noFlightLabel.setText("");
        String source = sourceTextFiled.getText();
        String destination = destinationTextField.getText();
        LocalDate date = datePicker.getValue();
        if (source.isEmpty() || destination.isEmpty()) {
            noFlightLabel.setText("Invalid Input");
            return;
        }

        // TODO: 4/9/2022 NOKI DO STUFF
        ArrayList<FlightInfo> allFlights = new ArrayList<>(); //Add all the available flights to this array  list
        ObservableList<FlightInfo> availableFlights = FXCollections.observableArrayList();
        FlightInfo tf = new FlightInfo(12, "A", "B", 200,
                "DH", "PH", LocalDate.of(2023, 1, 1), LocalTime.of(11, 30, 0));
        allFlights.add(tf);


        int availableCount = 0;
        for (FlightInfo flight : allFlights) {
            if (flight.getSource().equals(source) &&
                    flight.getDestination().equals(destination) &&
                    flight.getDepartureDate().isAfter(date)) {
                availableCount += 1;
                availableFlights.add(flight);
            }
        }
        if(availableCount > 0){
            flightDropDown.setItems(availableFlights);
        }
    }

    public void makeTicket() {

    }
}
