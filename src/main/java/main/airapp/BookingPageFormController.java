package main.airapp;

import database.repository.FlightRepository;
import database.repository.TicketRepository;
import datamodel.FlightInfo;
import datamodel.TicketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

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
        ticketClassDropDown.setItems(FXCollections.observableArrayList("First", "Business", "Economy"));
    }

    public void searchFlights() {
        errorLabel.setText("");
        String source = sourceTextFiled.getText();
        String destination = destinationTextField.getText();
        LocalDate date = datePicker.getValue();

        if (source.isEmpty() || destination.isEmpty()) {
            errorLabel.setText("Enter Source/Destination");
            return;
        }

        if (date == null || date.isBefore(LocalDate.now())) {
            errorLabel.setText("Invalid Date");
            return;
        }

        // checks for flights in 48hr range from selected date.
        ObservableList<FlightInfo> availableFlights = new FlightRepository().filterFlightAsObservableList(
                date.minusDays(1), date.plusDays(1), source, destination, null
        );
        if (availableFlights.size() > 0)
            errorLabel.setTextFill(Color.rgb(0, 200, 0));
        else errorLabel.setTextFill(Color.rgb(200, 0, 0));
        errorLabel.setText(availableFlights.size() + " Flight" + (availableFlights.size() == 1 ? "" : "s") + " Found");
        flightDropDown.setItems(availableFlights);

        // to show value in the dropdown bar
        flightDropDown.setConverter(new StringConverter<>() {
            @Override
            public String toString(FlightInfo flightInfo) {
                if (flightInfo == null) return "";
                return flightInfo.getId() + " " + flightInfo.getSource() + " - " + flightInfo.getDestination();
            }

            @Override
            public FlightInfo fromString(String s) {
                return null;
            }
        });

        // to show values in teh dropdown MENU. Copied from the documentation. this works. don't touch it.
        flightDropDown.setCellFactory(new Callback<>() {
            @Override
            public ListCell<FlightInfo> call(ListView<FlightInfo> flightInfoListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(FlightInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getId() + " " + item.getSource() + " - " + item.getDestination());
                        }
                    }
                };
            }
        });


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
        if (name.isEmpty()) {
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
        if (ticketClassDropDown.getSelectionModel().isEmpty()) {
            errorLabel.setText("Enter Ticket Class");
            return;
        }
        int seatNumber = flightInfo.getFirstSeatAvailable();
        if (seatNumber != -1) {
//            System.out.println(Arrays.toString(new String[]{name, email, country, passportNo, flightInfo.getId() + "", seatNumber + ""}));
            new TicketRepository().insert(new String[]{name, email, country, passportNo, flightInfo.getId() + "", seatNumber + "", "0"});
            flightInfo.confirmTicket();
            errorLabel.setTextFill(Color.rgb(0, 200, 0));
            errorLabel.setText("Ticket Successfully Booked");


            //Reseting all the textfield
            sourceTextFiled.setText(null);
            destinationTextField.setText(null);
            datePicker.setValue(null);
            numberOfTickets.setText(null);
            nameTextField.setText(null);
            passportNoTextField.setText(null);
            emailTexitField.setText(null);
            countryTextFiled.setText(null);

        } else {
            errorLabel.setTextFill(Color.rgb(200, 0, 0));
            errorLabel.setText("Seat Unavailable");
        }

    }
}
