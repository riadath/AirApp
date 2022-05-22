package main.airapp;

import datamodel.FlightInfo;
import datamodel.TicketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;

public class BookingPageFormController extends Controller {
    @FXML
    private Button bookTicketButton;
    @FXML
    private TextField countryTextFiled;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField destinationTextField;
    @FXML
    private TextField emailTexitField;
    @FXML
    private ComboBox<FlightInfo> flightDropDown;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label errorLabel;
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
    @FXML
    private Button exitButton;

    public void initialize() {
        ObservableList<String> ticketClassList = FXCollections.observableArrayList("First", "Business", "Economy");
        ticketClassDropDown.setItems(ticketClassList);
    }

    public void searchFlights() {
        errorLabel.setText("");
        String source = sourceTextFiled.getText();
        String destination = destinationTextField.getText();
        LocalDate date = datePicker.getValue();
        if (source.isEmpty() || destination.isEmpty()) {
            errorLabel.setText("Invalid Input");
            return;
        }

        // TODO: 4/9/2022 NOKI DO STUFF
        ArrayList<FlightInfo> allFlights = new ArrayList<>(); //Add all the available flights to this array  list
        ObservableList<FlightInfo> availableFlights = FXCollections.observableArrayList();
        FlightInfo tf = new FlightInfo(12, "A", "B", 200,
                "DH", "PH", LocalDate.of(2022, 1, 1), LocalTime.of(11, 30, 0));
        allFlights.add(tf);
        //TODO: add stuff pls


        int availableCount = 0;
        for (FlightInfo flight : allFlights) {
            if (flight.getSource().equals(source) &&
                    flight.getDestination().equals(destination) &&
                    flight.getDepartureDate().isEqual(date) &&
                    flight.getAvailableSeatNo() > 0) {
                availableCount += 1;
                availableFlights.add(flight);
            }
        }
        if (availableCount > 0) {
            flightDropDown.setItems(availableFlights);
        }
    }


    public void makeTicket() {
        String source = sourceTextFiled.getText();
        String dest = destinationTextField.getText();
        LocalDate date = datePicker.getValue();
        FlightInfo flightInfo = flightDropDown.getSelectionModel().getSelectedItem();
        String passengerNo = numberOfTickets.getText();
        String ticketClass = ticketClassDropDown.getSelectionModel().getSelectedItem();
        String name = nameTextField.getText();
        String passportNo = passportNoTextField.getText();
        String email = emailTexitField.getText();
        String country = countryTextFiled.getText();
        if (flightDropDown.getSelectionModel().isEmpty()) {
            errorLabel.setText("No Flight is Selected");
            return;
        }
        if (passengerNo.isEmpty()) {
            errorLabel.setText("Select Number of Passengers");
            return;
        }
        if(name.isEmpty()){
            errorLabel.setText("Enter Name");
            return;
        }
        if (passportNo.isEmpty()) {
            errorLabel.setText("Enter Passport No");
            return;
        }
        if (email.isEmpty()) {
            errorLabel.setText("Enter Email");
            return;
        }
        if (country.isEmpty()) {
            errorLabel.setText("Enter Country of Residence");
            return;
        }
        if(ticketClassDropDown.getSelectionModel().isEmpty()){
            errorLabel.setText("Enter Ticket Class");
            return;
        }
        int seatNumber = -1,cnt = 0;
        for(boolean i : flightInfo.getSeatAvailability()){
            if(!i){
                seatNumber = cnt + 1;
//                flightInfo.getSeatAvailability()[cnt] = true;
                //TODO : Change the seat availability on the database
                break;
            }
            cnt++;
        }
        TicketInfo ticketInfo = new TicketInfo(name,email,country,passportNo,flightInfo,seatNumber);

        exitButton.fire();
    }
}
