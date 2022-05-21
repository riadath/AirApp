package main.airapp;

import datamodel.FlightInfo;
import datamodel.TicketInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import main.airapp.Controller;

import java.util.ArrayList;

public class CheckinPageController extends Controller {
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
        String name = nameTextField.getText();
        String passport = passportTextField.getText();
        String luggage = luggageTextField.getText();
        if(name.isEmpty()){
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Enter the name of the passenger");
            return;
        }
        if(passport.isEmpty()){
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Enter the Passport number");
            return;
        }
        if(luggage.isEmpty()){
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("Enter the number of luggages");
            return;
        }

        ArrayList<TicketInfo>allTickets = new ArrayList<>();
        TicketInfo ticket = new TicketInfo("akib",
                "akib@gmail","BD","1234",
                new FlightInfo(12,"b","c",12),69);
        allTickets.add(ticket);

        //TODO: get all the tickets from db and do the stuff

        for(TicketInfo t_ticket : allTickets) {
            if (t_ticket.getName().equals(name) && t_ticket.getPassportNo().equals(passport)) {
                t_ticket.setTicketState(true);
                //TODO: ekhane ticket state true mane ticket used  eta database a push kor
                errorLabel.setTextFill(Paint.valueOf("green"));
                errorLabel.setText("Checked in!");

            }
        }



    }

}
