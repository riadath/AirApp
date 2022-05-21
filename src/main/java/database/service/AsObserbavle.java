package database.service;

import datamodel.AirplaneInfo;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AsObserbavle {

    public AsObserbavle() {}

    public ObservableList<FlightInfo> flightToObservable (ResultSet rs) {
        ObservableList<FlightInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {

                ZonedDateTime time = Instant.ofEpochMilli(rs.getInt("departure") * 1000L).atZone(ZoneId.systemDefault());

                ret.add(new FlightInfo(
                        rs.getInt("flightId"),
                        new AirplaneInfo(
                                rs.getInt("airplaneId"),
                                rs.getString("airplaneName"),
                                rs.getString("airplaneCode"),
                                rs.getInt("airplaneSeat")
                        ),
                        rs.getString("source"),
                        rs.getString("destination"),
                        time.toLocalDate(),
                        time.toLocalTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ObservableList<AirplaneInfo> airplaneToObservable (ResultSet rs) {
        ObservableList<AirplaneInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                ret.add(new AirplaneInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getInt("no_of_seats")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public  ObservableList<AirplaneInfo> airplaneToObservable (ResultSet rs, String manufacturer) {
        ObservableList<AirplaneInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                if (rs.getString("name").contains(manufacturer)) {
                    ret.add(new AirplaneInfo(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("code"),
                            rs.getInt("no_of_seats")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
