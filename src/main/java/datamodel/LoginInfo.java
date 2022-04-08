package datamodel;

import database.repository.AuthRepository;

public class LoginInfo {
    private final String username;
    private final String password;

    private final Integer admin = 1;
    private final Integer user = 2;

    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValid() {
        return new AuthRepository().adminAuth(username, password);
    }
}
