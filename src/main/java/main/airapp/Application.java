package main.airapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-panel-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 576);
//        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("AirApp");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
