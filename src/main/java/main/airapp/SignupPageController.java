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

public class SignupPageController {
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

    public void switchToLoginMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public boolean validateUserData(){

        /*TODO: check "SignupInfo" Class. All the information is
                stored in the signupInfo object.if the data is valid
                push them to db.
        */

        SignupInfo signupInfo = new SignupInfo(nameTextField.getText(),emailTextField.getText(),
                countryTextField.getText(),passportTextField.getText(),passwordTextField.getText(),
                confirmPassTextField.getText());
        String infoValidation = signupInfo.validateInfo();
        signupWarningLabel.setText(infoValidation);
        if (infoValidation.length() == 0) {
//            switchToLoginMenu();
            /*TODO: tis switches back to login page.
                switchTOLoginMenu function e  event.getSource ki kore idk eikhane thik kor
             */
        }
        return (infoValidation.equals(""));
    }

}
