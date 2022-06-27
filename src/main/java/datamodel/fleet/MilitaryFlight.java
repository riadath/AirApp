package datamodel.fleet;

import java.time.LocalDate;
import java.time.LocalTime;

public class MilitaryFlight extends FlightInfo{
    public MilitaryFlight(int id, String flightName, int noOfSeats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        super(id, flightName, noOfSeats, source, destination, departureDate, departureTime);
    }

    public MilitaryFlight(int id, AirplaneInfo airplane, String source, String destination, LocalDate date, LocalTime time, int remainingSeats) {
        super(id, airplane, source, destination, date, time, remainingSeats);
    }

    public MilitaryFlight(int id, String source, String destination, LocalDate date, LocalTime time, int remainingSeats) {
        super(id, source, destination, date, time, remainingSeats);
    }
}
