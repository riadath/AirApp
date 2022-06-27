package main.airapp;

import database.repository.TicketRepository;
import datamodel.TicketInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;


public class CheckinPageController extends Controller {

    @FXML
    private TextField ticketIdTextField;

    @FXML
    private TextField luggageTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passportTextField;

    @FXML
    private Label errorLabel;

    @FXML
    void checkIn(ActionEvent event) {
        String ticketId = ticketIdTextField.getText();
        String name = nameTextField.getText();
        String passport = passportTextField.getText();
        String luggage = luggageTextField.getText();

        errorLabel.setTextFill(Paint.valueOf("red"));

        if (ticketId.isEmpty()) {
            errorLabel.setText("Ticket Id is empty");
            return;
        }

        if(name.isEmpty()){
            errorLabel.setText("Enter the name of the passenger");
            return;
        }
        if(passport.isEmpty()){
            errorLabel.setText("Enter the Passport number");
            return;
        }
        if(luggage.isEmpty()){
            errorLabel.setText("Enter the number of luggage");
            return;
        }

        TicketRepository ticketRepository = new TicketRepository();

        TicketInfo ticketInfo = ticketRepository.getTicket(Integer.parseInt(ticketId));

        if (ticketInfo == null) {
            errorLabel.setText("No Ticket Found!");
            return;
        }

        if (!ticketInfo.verifyUser(name, passport)) {
            errorLabel.setText("Ticket Information Mismatch");
            return;
        }

        if (Integer.parseInt(luggage) > 36) {
            errorLabel.setText("Overweight");
            return;
        }

        ticketInfo.setCheckIn();
        errorLabel.setTextFill(Paint.valueOf("green"));
        errorLabel.setText("Checked in!");

    }

}
