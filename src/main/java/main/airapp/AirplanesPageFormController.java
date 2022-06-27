package main.airapp;

import database.repository.TicketRepository;
import datamodel.AirplaneInfo;
import datamodel.FlightInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class AirplanesPageFormController extends Controller {

    @FXML
    private TextField AirplaneCodeTextField;

    @FXML
    private TextField AirplaneNameTextField;

    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField NoOfSeatsTextField;

    @FXML
    private Label errorlabel;

    @FXML
    private Button AirplaneFormExit;


    public void makeAirplane() {
        String Name = AirplaneNameTextField.getText();
        String code = AirplaneCodeTextField.getText();
        Integer NoOfSeats = -1;
        if( ! NoOfSeatsTextField.getText().isEmpty() ){
            NoOfSeats = Integer.parseInt( NoOfSeatsTextField.getText() );
        }
        Integer id;
        System.out.println(Name);
        System.out.println(NoOfSeats);

        if( Name.isEmpty() ){
            errorlabel.setText("Enter Airplane Name");
        }
        else if( code.isEmpty() ){
            errorlabel.setText("Enter Airplane Code");
        }
        else if( NoOfSeatsTextField.getText().isEmpty() ){
            errorlabel.setText("Enter No Of Seats");
        }
        else{
            errorlabel.setTextFill(Color.GREEN);
            errorlabel.setText("Airplane Added Successfully!!");
        }
    }

}
