package datamodel;
import database.*;

public class LoginInfo {
    private final String username;
    private final String password;


    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isValid() {
        /*  TODONE: NOKI
            Validate the password and username
//        */
        System.out.println(DB.auth(this.username, this.password));

        return true;
    }
}
