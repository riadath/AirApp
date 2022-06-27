package database.repository;


import database.DB;
import datamodel.customer.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository extends DB {
    public AuthRepository() {
        super();
    }

    static private final String admin_acc_table_name = "account_info";
    static private final String user_acc_table_name = "user_info";

    static UserInfo user;

    public boolean adminAuth(String username, String password) {
        final String adminAuthQuery = "SELECT username,password FROM " + admin_acc_table_name + " WHERE username=\"" + username
                + "\" AND password=\"" + password + "\";";
        ResultSet account = select_query(adminAuthQuery);
        try {
            return !account.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean userAuth(String email, String password) {
        final String userAuthQuery = "SELECT *  FROM " + user_acc_table_name + " WHERE email=\"" + email
                + "\" AND password=\"" + password + "\";";
        ResultSet account = select_query(userAuthQuery);
        try {

            user = new UserInfo(account);
            System.out.println(user);


            return account.getString("email").equals(email) &&
                    account.getString("password").equals(password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserInfo getUser() { return user;}
}
