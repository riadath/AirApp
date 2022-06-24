package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightInfo {
    private int id;
    private String flightName;
    private String flightCode;
    private int noOfSeats;
    private AirplaneInfo airplane;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private Boolean[] seatAvailability;
    private int availableSeatNo;


    public FlightInfo(int id, String flightName, String flightCode, int noOfSeats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.id = id;
        this.flightName = flightName;
        this.flightCode = flightCode;
        this.noOfSeats = noOfSeats;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.seatAvailability = new Boolean[noOfSeats];
        this.availableSeatNo = noOfSeats;
    }

    public FlightInfo(int id, String flightName, int noOfSeats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.id = id;
        this.flightName = flightName;
        this.noOfSeats = noOfSeats;
        this.airplane = new AirplaneInfo(-1, flightName, "21", noOfSeats);
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.seatAvailability = new Boolean[noOfSeats];
        this.availableSeatNo = noOfSeats;
    }

    public FlightInfo(int id, String flightName, String flightCode, int noOfSeats) {
        this.id = id;
        this.flightName = flightName;
        this.flightCode = flightCode;
        this.noOfSeats = noOfSeats;
        this.seatAvailability = new Boolean[noOfSeats];
        this.availableSeatNo = noOfSeats;
    }

    public FlightInfo(int id, AirplaneInfo airplane, String source, String destination, LocalDate date, LocalTime time) {
        this.id = id;
        this.airplane = airplane;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.departureTime = time;
    }

    public int getId() {
        return id;
    }

    public AirplaneInfo getAirplane() {
        return airplane;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getFlightCode() {
        return flightCode;
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

    public Boolean[] getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(Boolean[] seatAvailability) {
        this.seatAvailability = seatAvailability;
    }

    public int getAvailableSeatNo() {
        return availableSeatNo;
    }

    public void setAvailableSeatNo(int availableSeatNo) {
        this.availableSeatNo = availableSeatNo;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "id=" + id +
                ", flightName='" + flightName + '\'' +
                ", flightCode='" + flightCode + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", airplane=" + airplane +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                '}';
    }
}
