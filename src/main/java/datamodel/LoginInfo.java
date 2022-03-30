package datamodel;

import database.repository.AuthRepository;

public class LoginInfo {
    private final String username;
    private final String password;


    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValid() {
        /* TODO: user ar admin auth er jonne different scene e switch korbe

         */
        return username.contains(".") ? new AuthRepository().userAuth(username, password) :
                new AuthRepository().adminAuth(username, password);
    }
}
