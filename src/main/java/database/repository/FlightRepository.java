package database.repository;

import database.DB;
import database.service.AsObserbavle;
import datamodel.AirplaneInfo;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

public class FlightRepository extends DB {

    static String table_name = "Flight";
    private AsObserbavle asObserbavle = new AsObserbavle();

    public FlightRepository() {
        super();
    }

    public ObservableList<FlightInfo> flightAsObservableList() {
        final String query = "select Flight.id as flightId, Airplane.id as airplaneId, Airplane.name as airplaneName,"
                + " Airplane.code as airplaneCode, Airplane.no_of_seats as airplaneSeat,"
                + " Flight.source, Flight.destination, Flight.departure from Flight"
                + " left join Airplane on Airplane.code = Flight.airplane";
        return asObserbavle.flightToObservable(select_query(query));
    }

    public ObservableList<FlightInfo> filterFlightAsObservableList(LocalDate from, LocalDate to, String source, String destination, String airplaneName) {
        final String query =
                "select Flight.id as flightId, Flight.source, Flight.destination, Flight.departure" +
                " Airplane.id as airplaneId, Airplane.name as airplaneName," +
                " Airplane.code as airplaneCode, Airplane.no_of_seats as airplaneSeat," +
                " left join Airplane on Airplane.code = Flight.airplane" +
                " from Flight" +
                " where departure >= " + from.toEpochSecond(LocalTime.MIN, ZoneOffset.MIN) +
                " and departure <= " + to.toEpochSecond(LocalTime.MIN, ZoneOffset.MIN) +
                (airplaneName == null ? "" : " and Airplane.name=\"" + airplaneName + "\"") +
                (source == null ? "" : " and Flight.source=\"" + source + "\"") +
                (destination == null ? "" : " and Flight.destination=\"" + destination + "\"");
        return asObserbavle.flightToObservable(select_query(query));
    }

    public void insert(String[] values) {
        super.insert(table_name, values);
    }

}
