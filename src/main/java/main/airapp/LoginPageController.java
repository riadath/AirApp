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

public class LoginPageController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public Button signUpButton;

    @FXML
    public void switchToUserMenu(ActionEvent event) throws IOException {
        LoginInfo loginInfo = new LoginInfo(usernameTextField.getText(), passwordField.getText());

        if (!loginInfo.isValid()) {
            return;
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-menu-page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}