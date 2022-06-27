package database.repository;

import database.DB;
import database.service.AsObservable;
import datamodel.fleet.FlightInfo;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;

public class FlightRepository extends DB {

    static String table_name = "Flight";
    private final AsObservable asObservable = new AsObservable();

    public FlightRepository() {
        super();
    }

    public ObservableList<FlightInfo> flightAsObservableList() {
        final String query = "select Flight.id as flightId, Airplane.id as airplaneId, Airplane.name as airplaneName,"
                + " Airplane.code as airplaneCode, Airplane.no_of_seats as airplaneSeat,"
                + " Flight.source, Flight.destination, Flight.departure, Flight.name, Flight.remainingSeats from Flight"
                + " left join Airplane on Airplane.code = Flight.airplane";
        return asObservable.flightToObservable(select_query(query));
    }

    public ObservableList<FlightInfo> filterFlightAsObservableList(LocalDate from, LocalDate to, String source, String destination, String airplaneName) {
        final String query =
                "select Flight.id as flightId, Flight.source, Flight.destination, Flight.departure, Flight.remainingSeats," +
                " Airplane.id as airplaneId, Airplane.name as airplaneName," +
                " Airplane.code as airplaneCode, Airplane.no_of_seats as airplaneSeat" +
                        " from Flight" +
                " left join Airplane on Airplane.code = Flight.airplane" +
                " where departure >= " + from.toEpochSecond(LocalTime.MIN, ZoneOffset.MIN) +
                " and departure <= " + to.toEpochSecond(LocalTime.MIN, ZoneOffset.MIN) +
                (airplaneName == null ? "" : " and Airplane.name=\"" + airplaneName + "\"") +
                (source == null ? "" : " and Flight.source=\"" + source + "\"") +
                (destination == null ? "" : " and Flight.destination=\"" + destination + "\"");
        return asObservable.flightToObservable(select_query(query));
    }

    public ArrayList<Integer> getHistory (String flightId) {
        final String query = "select id from Flight where flightId=" + flightId;
        return getIntegers(query);
    }


    private ArrayList<Integer> getIntegers(String query) {
        ResultSet rs = select_query(query);
        ArrayList<Integer> ret = new ArrayList<>();

        try {
            while (rs.next()) {
                ret.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<Integer> getTendency (String flightClass) {
        final String query = "select id from Flight where flightClass=" + flightClass;
        return getIntegers(query);
    }

    public void insert(String[] values) {
        super.insert(table_name, values);
    }

    public void updateNoOfSeats (int id, int newValue) {
        final String query = "update Flight set remainingSeats=" + newValue + " where id=" + id;
        super.update_query(query);
    }

}
