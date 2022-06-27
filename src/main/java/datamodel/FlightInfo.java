package datamodel;

import database.repository.FlightRepository;
import database.repository.TicketRepository;

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

    public FlightInfo(int id, AirplaneInfo airplane, String source, String destination, LocalDate date, LocalTime time, int remainingSeats) {
        this.id = id;
        this.airplane = airplane;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.departureTime = time;
        this.availableSeatNo= remainingSeats;
    }

    public FlightInfo(int id, String source, String destination, LocalDate date, LocalTime time, int remainingSeats) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.departureTime = time;
        this.availableSeatNo= remainingSeats;
    }

    public FlightInfo( String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.airplane = airplane;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public AirplaneInfo getAirplane() {
        return airplane;
    }

    public String getFlightName() {
        return id + " "  + source + " - " + destination;
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

    public int getFirstSeatAvailable () {
        return (this.availableSeatNo > 0 ? airplane.getNumber_of_seats() - this.availableSeatNo + 1 : -1);
    }

    public void confirmTicket () {
        availableSeatNo = availableSeatNo - 1;
        new FlightRepository().updateNoOfSeats(this.id, availableSeatNo);
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
