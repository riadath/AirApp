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
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label wrongPassLabel;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void switchToUserMenu(ActionEvent event) throws IOException {
        LoginInfo loginInfo = new LoginInfo(usernameTextField.getText(), passwordField.getText());

        if (!loginInfo.isValid()) {
            wrongPassLabel.setText("Username or Password incorrect!");
            return;
        }
        wrongPassLabel.setText("");
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-menu-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToSignupPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-up-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}