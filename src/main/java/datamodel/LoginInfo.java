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

    public Integer isValid() {
        /* TODO: user ar admin auth er jonne different scene e switch korbe

         */
        int ret = 0;

        if (username.contains(".")) {
            System.out.println(username + " " + password);
            if (new AuthRepository().userAuth(username, password)) {
                ret = 1;
            }
        } else {
            if (new AuthRepository().adminAuth(username, password)) {
                ret = 2;
            }
        }
        return ret;
    }
}
