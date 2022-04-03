package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightInfo {
    private int id;
    private String flightName;
    private int noOfSeats;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;

    public FlightInfo(int id, String flightName, int noOfSeats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.id = id;
        this.flightName = flightName;
        this.noOfSeats = noOfSeats;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public FlightInfo(int id, String flightName, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.id = id;
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public FlightInfo(int id, String flightName) {
        this.id = id;
        this.flightName = flightName;
    }

    public int getId() {
        return id;
    }

    public String getFlightName() {
        return flightName;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
}
