package database.repository;


import database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository extends DB {
    public AuthRepository() {
        super();
    }

    static String repository_name = "account_info";

    public ResultSet list () {
        String query_statement = "SELECT * FROM " + repository_name + ";";
        return query (query_statement);
    }

    public boolean auth (String username, String password) {
        ResultSet accounts = list();
        boolean authCond = false;
        try {
            while (!authCond && accounts.next()) {
                authCond = accounts.getString("username").equals(username) &&
                        accounts.getString("password").equals(password);
            }
        } catch (SQLException e) {
            authCond = false;
            e.printStackTrace();
        }
        return authCond;
    }

}
