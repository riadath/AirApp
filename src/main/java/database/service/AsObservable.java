package database.service;

import datamodel.fleet.AirplaneInfo;
import datamodel.fleet.FlightInfo;
import datamodel.customer.TicketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AsObservable {

    public AsObservable() {}

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
                        time.toLocalTime(),
                        rs.getInt("remainingSeats")
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

    public ObservableList<AirplaneInfo> filterAirplaneToObservable (ResultSet rs) {
        ObservableList<AirplaneInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                AirplaneInfo airplane = new AirplaneInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getInt("no_of_seats")
                );
                if (!ret.contains(airplane)){
                    ret.add(airplane);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ObservableList<TicketInfo> ticketToObservable (ResultSet rs) {

        ObservableList<TicketInfo> ret = FXCollections.observableArrayList();

        try {
            while (rs.next()) {

                ZonedDateTime time = Instant.ofEpochMilli(rs.getInt("departure") * 1000L).atZone(ZoneId.systemDefault());

                ret.add(new TicketInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("countryOfResidence"),
                        rs.getString("passportNo"),
                        new FlightInfo(
                                rs.getInt("flightId"),
                                rs.getString("source"),
                                rs.getString("destination"),
                                time.toLocalDate(),
                                time.toLocalTime(),
                                rs.getInt("remainingSeats")
                        ),
                        rs.getInt("seatNumber"),
                        rs.getBoolean("checkedIn")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
