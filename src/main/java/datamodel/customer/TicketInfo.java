package datamodel.customer;

import database.repository.TicketRepository;
import datamodel.fleet.FlightInfo;

import java.util.ArrayList;

public class TicketInfo implements CustomerService {


    private int id;
    FlightInfo flightInfo;
    private boolean ticketState;
    private final int seatNumber;
    private final UserInfo user;
    TicketRepository ticketRepository;

    public TicketInfo(int id, String name, String email, String countryOfResidence,
                      String passportNo, FlightInfo flightInfo,int seatNumber) {
        user = new UserInfo(name, email, countryOfResidence, passportNo);
        this.id = id;
        this.flightInfo = flightInfo;
        this.ticketState = false;
        this.seatNumber = seatNumber;
        ticketRepository = new TicketRepository();
    }

    public TicketInfo(int id, String name, String email, String countryOfResidence,
                      String passportNo, int seatNumber, boolean checkedIn) {
        user = new UserInfo(name, email, countryOfResidence, passportNo);
        this.id = id;
        this.ticketState = checkedIn;
        this.seatNumber = seatNumber;
    }

    public ArrayList<Integer> getHistory (String flight) {
        return ticketRepository.getHistory(flight);
    };

    public int getId() {
        return id;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public ArrayList<Integer> getTendency (String flightClass) {
        return ticketRepository.getTendency(flightClass);

    };

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }

    public boolean isCheckedIn() {
        return ticketState;
    }

    public void setTicketState(boolean ticketState) {
        this.ticketState = ticketState;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getPassengerDetails () {
        return user.getName() + " - " + user.getPassportNo() + "; Seat - " + getSeatNumber();
    }

    public void setCheckIn () {
        ticketRepository.setCheckIn(this.id);
    }

    public boolean verifyUser(String name, String passport) {
        return (user.getName().equals(name) && user.getPassportNo().equals(passport));
    }
}
