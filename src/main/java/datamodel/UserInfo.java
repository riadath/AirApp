package datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfo {
    private String name;
    private String email;
    private String countryOfResidence;
    private String passportNo;
    private String password;

    public UserInfo(String name, String email, String countryOfResidence, String passportNo, String password) {
        this.name = name;
        this.email = email;
        this.countryOfResidence = countryOfResidence;
        this.passportNo = passportNo;
        this.password = password;
    }

    public UserInfo(ResultSet userInfo) {
        try {
            name = userInfo.getString("name");
            email = userInfo.getString("email");
            countryOfResidence = userInfo.getString("countryOfResidence");
            passportNo = userInfo.getString("passportNo");
            password = userInfo.getString("password");

        } catch  (SQLException e) {
            System.out.println("User Initialization failed. ALl values set to null");

            name = null;
            email = null;
            countryOfResidence = null;
            passportNo = null;
            password = null;

            e.printStackTrace();
        }
    }

    public String[] toArray() {
        return new String[]{
                name, email, countryOfResidence, passportNo, password
        };
    }

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getCountryOfResidence() {return countryOfResidence;}

    public String getPassportNo() {return passportNo;}

    public String getPassword() {return password;}
}
