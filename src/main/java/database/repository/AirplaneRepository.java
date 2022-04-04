package database.repository;

import database.DB;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirplaneRepository extends DB {

    static String table_name = "Airplane";

    public AirplaneRepository() {super();}

    public ObservableList<FlightInfo> airplaneAsObservable () {
        ResultSet rs = list(table_name);
        ObservableList<FlightInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                ret.add( new FlightInfo(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
