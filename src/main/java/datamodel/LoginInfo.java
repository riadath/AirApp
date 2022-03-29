package datamodel;
import database.*;
import database.repository.AuthRepository;

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
        return new AuthRepository().auth(username, password);
    }
}
