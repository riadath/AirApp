package datamodel.customer;

import datamodel.Luggage;

import java.sql.ResultSet;

public class Passenger extends UserInfo{

    private TicketInfo ticket;
    private Luggage luggage;

    public Passenger(String name, String email, String countryOfResidence, String passportNo, String luggageMass) {
        super(name, email, countryOfResidence, passportNo);
        luggage = new Luggage(Integer.parseInt(luggageMass));
    }

    public Passenger(ResultSet userInfo) {
        super(userInfo);
    }

    public boolean checkTicketValidity () {
        return ticket.isCheckedIn();
    }

    public boolean checkLuggageValidity () {
        return luggage.checkAcceptableMass();
    }
}
