package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightInfo {
    private int id;
    private String FlightName;
    private int No_Of_Seats;
    private String Source;
    private String Destination;
    private LocalDate DepartureDate;
    private LocalTime DepartureTime;

    public FlightInfo(int id, String flightName, int no_Of_Seats, String source, String destination, LocalDate departureDate, LocalTime departureTime) {
        this.id = id;
        FlightName = flightName;
        No_Of_Seats = no_Of_Seats;
        Source = source;
        Destination = destination;
        DepartureDate = departureDate;
        DepartureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public String getFlightName() {
        return FlightName;
    }

    public int getNo_Of_Seats() {
        return No_Of_Seats;
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }

    public LocalDate getDepartureDate() {
        return DepartureDate;
    }

    public LocalTime getDepartureTime() {
        return DepartureTime;
    }
}
