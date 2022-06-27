package main.airapp;

import database.repository.AirplaneRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AirplanesPageFormController extends Controller {

    @FXML
    private TextField AirplaneCodeTextField;

    @FXML
    private TextField AirplaneNameTextField;

    @FXML
    private TextField NoOfSeatsTextField;

    @FXML
    private Label errorlabel;


    public void makeAirplane() {
        String name = AirplaneNameTextField.getText();
        String code = AirplaneCodeTextField.getText();
        int NoOfSeats;
        if (!NoOfSeatsTextField.getText().isEmpty()) {
            try {
                NoOfSeats = Integer.parseInt(NoOfSeatsTextField.getText());
            } catch (NumberFormatException e) {
                errorlabel.setText("Number Of Seats Must be an Integer");
                return;
            }
        } else {
            errorlabel.setText("Enter Number Of Seats");
            return;
        }

        if (name.isEmpty()) {
            errorlabel.setText("Enter Airplane Name");
            return;
        } else if (code.isEmpty()) {
            errorlabel.setText("Enter Airplane Code");
            return;
        }

        new AirplaneRepository().insert(new String[]{name, code, NoOfSeats + ""});

        errorlabel.setTextFill(Color.GREEN);
        errorlabel.setText("Airplane Added Successfully!!");

    }

}
