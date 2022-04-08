package main.airapp;

import database.DB;
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
        boolean validityCheck = loginInfo.isValid();
        if (!validityCheck) {
            wrongPassLabel.setText("Username or Password incorrect!");
            return;
        }
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-panel-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


}
