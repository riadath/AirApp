package datamodel.fleet;

import datamodel.Luggage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CivilianFlight extends FlightInfo{

    private final List<Integer> passengerList = new ArrayList<>();
    private Luggage luggage = new Luggage(21);

    public CivilianFlight(int id, String source, String destination, LocalDate date, LocalTime time, int remainingSeats) {
        super(
                id,
                source,
                destination,
                date,
                time,
                remainingSeats
        );
    }

    public CivilianFlight(int id, String flightName, int noOfSeats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        super(
                id,
                flightName,
                noOfSeats,
                source,
                destination,
                departureDate,
                departureTime
        );
        luggage = new Luggage(21);
    }

    public CivilianFlight(int id, AirplaneInfo airplane, String source, String destination, LocalDate date, LocalTime time, int remainingSeats) {
        super(
                id,
                airplane,
                source,
                destination,
                date,
                time,
                remainingSeats
        );
    }


    public boolean checkLuggageWeight () {
        return luggage.checkAcceptableMass();
    }

}
