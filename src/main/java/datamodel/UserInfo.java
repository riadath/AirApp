package datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfo {
    private String name;
    private String email;
    private String country;
    private String passportNo;

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

    public String[] toArray() {
        return new String[]{
                name, email, country, passportNo
        };
    }

    @Override
    public String toString() {
        return name + " "  + email + " " + country + " " + passportNo;
    }


    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getCountry() {return country;}

    public String getPassportNo() {return passportNo;}

}
