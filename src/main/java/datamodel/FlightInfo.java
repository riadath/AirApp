package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightInfo {
    private int id;
    private String flightName;
    private String flightCode;
    private int noOfSeats;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;

    public FlightInfo(int id, String flightName, String flightCode, int noOfSeats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.id = id;
        this.flightName = flightName;
        this.flightCode = flightCode;
        this.noOfSeats = noOfSeats;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

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

    public FlightInfo(int id, String flightName, String flightCode, int noOfSeats) {
        this.id = id;
        this.flightName = flightName;
        this.flightCode = flightCode;
        this.noOfSeats = noOfSeats;
    }

    public int getId() {
        return id;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getFlightCode() {return flightCode;}

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

    @Override
    public String toString(){
        return "ID : " + id + " Flight Name : " + flightName +
                " Flight Code : " + flightCode + " Date : " + departureDate.toString();
    }
}
