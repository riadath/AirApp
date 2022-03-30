package main.airapp;

import datamodel.SignupInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Objects;

public class SignupPageController extends Controller{
    @FXML
    private Button exitFromSignupButton;
    @FXML
    private Label signupWarningLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField passportTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPassTextField;


    @FXML
    public void validateUserData(){
        SignupInfo signupInfo = new SignupInfo(nameTextField.getText(),emailTextField.getText(),
                countryTextField.getText(),passportTextField.getText(),passwordTextField.getText(),
                confirmPassTextField.getText());
        String infoValidation = signupInfo.validateInfo();
        if(infoValidation.length() == 0) {
            exitFromSignupButton.fire();
        }
        else {
            signupWarningLabel.setText(infoValidation);
        }

    }

}
