module main.airapp {
    requires javafx.controls;
    requires javafx.fxml;
//    requires sqlite-jdbc;
    requires java.sql;


    opens main.airapp to javafx.fxml;
    exports main.airapp;
}
