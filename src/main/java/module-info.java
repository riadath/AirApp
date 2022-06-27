module main.airapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.airapp to javafx.fxml;
    opens datamodel to javafx.fxml;

    exports main.airapp;
    exports datamodel;
    exports datamodel.fleet;
    opens datamodel.fleet to javafx.fxml;
    exports datamodel.customer;
    opens datamodel.customer to javafx.fxml;
}
