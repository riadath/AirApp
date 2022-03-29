package database;


import java.sql.*;

public class DB {
    private static final String dbLink = "jdbc:sqlite:src/main/resources/AirAppDb.db";
    static Connection connection;
    static Statement stmt;

    public DB() {
        create_connection();
    }

    private void create_connection() {
        try {
            connection.isValid(3500);
        } catch (Exception e) {
            System.out.println("Creating database connection with " + dbLink);
            try {
                connection = DriverManager.getConnection(dbLink);
                stmt = connection.createStatement();
            } catch (SQLException ex) {
                System.out.println("Could not create connection");
                ex.printStackTrace();
            }
        }
    }

    public ResultSet query(String query) {
        ResultSet res;
        try {
            res = stmt.executeQuery(query);
            return res;
        } catch (SQLException e) {
            System.out.println("Database query failed for query: ");
            System.out.println("<---" + query + "---->");
            return null;
        }
    }
}

/*
* package database;

import database.repository.authRepository;

import java.sql.*;

public class DB {

    static String dbLink = "jdbc:sqlite:src/main/resources/AirAppDb.db";
    static authRepository connection;
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
* */

