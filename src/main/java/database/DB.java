package database;

import java.sql.*;

public class DB {

    static String dbLink = "jdbc:sqlite:src/main/resources/AirAppDb.db";
    static Connection connection;
    static Statement stmt;
    static boolean authCond;


    public static boolean auth (String username, String password) {
        authCond = false;
        try {
            connection = DriverManager.getConnection(dbLink);

            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("select * from account_info");

            while(!authCond  && result.next()) {
                authCond = result.getString("username").equals(username) && result.getString("password").equals(password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return authCond;
        }
    }
}
