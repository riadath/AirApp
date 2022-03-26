package datamodel;

import java.util.ArrayList;

public class LoginInfo {
    private String username;
    private String password;


    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isValid() {
        /* TODO: NOKI
            Validate the password and username
        */

        return true;
    }
}
