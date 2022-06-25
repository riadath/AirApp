package main.airapp;

import database.repository.FlightRepository;
import database.repository.TicketRepository;
import datamodel.FlightInfo;
import datamodel.TicketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.Arrays;

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
            errorLabel.setText("Invalid Input");
            return;
        }

        if (date == null || date.isBefore(LocalDate.now())) {
            errorLabel.setText("Invalid Date");
            return;
        }

        // checks for flights in 48hr range from selected date.
        ObservableList<FlightInfo> availableFlights =  new FlightRepository().filterFlightAsObservableList(
                date.minusDays(1), date.plusDays(1), source, destination, null
        );
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

        // TODO: available flight er length >0 hoile error label er color green maybe?

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
        int seatNumber = flightInfo.getFirstSeatAvailable();
        if (seatNumber > -1) {
//            System.out.println(Arrays.toString(new String[]{name, email, country, passportNo, flightInfo.getId() + "", seatNumber + ""}));
            new TicketRepository().insert(new String[]{name, email, country, passportNo, flightInfo.getId() + "", seatNumber + "", "0"});
            flightInfo.confirmTicket();
//            TicketInfo ticketInfo = new TicketInfo(name,email,country,passportNo,flightInfo,seatNumber);
        }
        else {
            // TODO : Seat shesh hoye gele error label
        }

        // TODO : exitButton fire na kore ekta confirmation or rejection label. Seat er theke ei label alada.

//        exitButton.fire();
    }
}
