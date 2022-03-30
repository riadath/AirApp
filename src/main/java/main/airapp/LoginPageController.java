package main.airapp;

import datamodel.LoginInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class LoginPageController extends Controller{
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label wrongPassLabel;

    @FXML
    public void switchToMenu(ActionEvent event) throws IOException {

        LoginInfo loginInfo = new LoginInfo(usernameTextField.getText(), passwordField.getText());
        int validityCheck = loginInfo.isValid();
        if (validityCheck == 0) {
            wrongPassLabel.setText("Username or Password incorrect!");
            return;
        }
        wrongPassLabel.setText("");
        String fxmlPath = "admin-panel-page.fxml";
        if(validityCheck == 2){
            fxmlPath = "user-menu-page.fxml";
        }
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


}