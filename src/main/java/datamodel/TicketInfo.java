package datamodel;

import java.sql.ResultSet;

public class TicketInfo extends UserInfo {

    FlightInfo flightInfo;

    public TicketInfo(String name, String email, String countryOfResidence,
                      String passportNo, FlightInfo flightInfo) {
        super(name, email, countryOfResidence, passportNo);
        this.flightInfo = flightInfo;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }
}
