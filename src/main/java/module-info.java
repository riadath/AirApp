module main.airapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.airapp to javafx.fxml;
    exports main.airapp;
}