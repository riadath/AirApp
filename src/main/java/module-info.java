module main.airapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.airapp to javafx.fxml;
    opens datamodel to javafx.fxml;

    exports main.airapp;
    exports datamodel;
}
