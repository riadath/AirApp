package database.repository;

import database.DB;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class FlightRepository extends DB {
    static String table_name = "Flight";

    public FlightRepository() {super();}

    public ObservableList<FlightInfo> flightAsObservableList () {
        final String query = "select Flight.id, Airplane.name, Flight.source, Flight.destination, Flight.departure from Flight " +
                "left join Airplane on Airplane.code = Flight.airplane";
        ResultSet rs = select_query(query);
        ObservableList<FlightInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                ret.add(new FlightInfo(rs.getInt("id"), rs.getString("name"),
                        rs.getString("source"), rs.getString("destination"),
                        LocalDate.now(), LocalTime.now()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
