package datamodel.customer;

import database.repository.FlightRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInfo implements CustomerService{
    private String name;
    private String email;
    private String country;
    private String passportNo;

    private final FlightRepository flightRepository = new FlightRepository();

    public UserInfo(String name, String email, String countryOfResidence, String passportNo) {
        this.name = name;
        this.email = email;
        this.country = countryOfResidence;
        this.passportNo = passportNo;
    }

    public UserInfo(ResultSet userInfo) {
        try {
            name = userInfo.getString("name");
            email = userInfo.getString("email");
            country = userInfo.getString("country");
            passportNo = userInfo.getString("passportNo");
        } catch  (SQLException e) {
            System.out.println("User Initialization failed. ALl values set to null");

            name = null;
            email = null;
            country = null;
            passportNo = null;

            e.printStackTrace();
        }
    }

    public UserInfo() { }

    public String[] toArray() {
        return new String[]{
                name, email, country, passportNo
        };
    }

    @Override
    public String toString() {
        return name + " "  + email + " " + country + " " + passportNo;
    }

    public ArrayList<Integer> getHistory (String flightId) {
        return flightRepository.getHistory(flightId);
    };
    public ArrayList<Integer> getTendency (String flightClass) {
        return  flightRepository.getTendency(flightClass);
    };

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getCountry() {return country;}

    public String getPassportNo() {return passportNo;}

    public boolean verifyUser (String name, String passportNo) {
        return this.name.equals(name) && this.passportNo.equals(passportNo);
    }

}
