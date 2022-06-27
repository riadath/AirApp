package datamodel;

import database.repository.TicketRepository;

public class TicketInfo extends UserInfo {

    private int id;
    FlightInfo flightInfo;
    private boolean ticketState;
    private final int seatNumber;

    public TicketInfo(int id, String name, String email, String countryOfResidence,
                      String passportNo, FlightInfo flightInfo,int seatNumber) {
        super(name, email, countryOfResidence, passportNo);
        this.id = id;
        this.flightInfo = flightInfo;
        this.ticketState = false;
        this.seatNumber = seatNumber;
    }

    public TicketInfo(int id, String name, String email, String countryOfResidence,
                      String passportNo, int seatNumber, boolean checkedIn) {
        super(name, email, countryOfResidence, passportNo);
        this.id = id;
        this.ticketState = checkedIn;
        this.seatNumber = seatNumber;
    }

    public TicketInfo(String name, String email, String countryOfResidence,
                      String passportNo, FlightInfo flightInfo,int seatNumber) {
        super(name, email, countryOfResidence, passportNo);
        this.flightInfo = flightInfo;
        this.ticketState = false;
        this.seatNumber = seatNumber;
    }


    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }

    public boolean isTicketState() {
        return ticketState;
    }

    public void setTicketState(boolean ticketState) {
        this.ticketState = ticketState;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getPassengerDetails () {
        return getSeatNumber() + " - " + getName() + " - " + getPassportNo();
    }

    public void setCheckIn () {
        new TicketRepository().setCheckIn(this.id);
    }

}
