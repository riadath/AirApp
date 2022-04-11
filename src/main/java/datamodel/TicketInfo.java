package datamodel;

import java.sql.ResultSet;

public class TicketInfo extends UserInfo {

    FlightInfo flightInfo;
    private boolean ticketState;

    public TicketInfo(String name, String email, String countryOfResidence,
                      String passportNo, FlightInfo flightInfo) {
        super(name, email, countryOfResidence, passportNo);
        this.flightInfo = flightInfo;
        this.ticketState = false;
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
}
