package database;


import java.sql.*;
import java.util.HashSet;
import java.util.Set;

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
            try {
                connection = DriverManager.getConnection(dbLink);
                stmt = connection.createStatement();
            } catch (SQLException ex) {
                System.out.println("Could not create connection with link " + dbLink);
                ex.printStackTrace();
            }
        }
    }

    public ResultSet select_query(String query) {
        ResultSet res;
        try {
            res = stmt.executeQuery(query);
            return res;
        } catch (SQLException e) {
            System.out.println("Database query failed for query: ");
            System.out.println("<---" + query + "---->");
            System.out.println(e.getSQLState());
            return null;
        }
    }

    public void insert_query (String query) {
        try {
            connection.prepareStatement(query).executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert query failed for query: ");
            System.out.println("<---" + query + "---->");
            System.out.println(e.getSQLState());
        }
    }

    public void update_query (String query) {
        try {
            connection.prepareStatement(query).executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert query failed for query: ");
            System.out.println("<---" + query + "---->");
            System.out.println(e.getSQLState());
        }
    }

    public String[] getColAsString(String col, String table_name) {
        final String query = "SELECT " + col + " FROM " + table_name;
        ResultSet rs = select_query(query);
        Set<String> ret = new HashSet<>();
        try {
            while (rs.next()) {
                ret.add(rs.getString(col));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] retString = new String[ret.size()];
        return ret.toArray(retString);
    }

    public ResultSet list(String table_name) {
        String query_statement = "SELECT * FROM " + table_name + ";";
        return select_query(query_statement);
    }

    public void insert(String table_name, String[] values) {
        StringBuilder query_statement = new StringBuilder("INSERT INTO " + table_name + " (");
        String column_statement = "PRAGMA table_info (" + table_name + ")";

        ResultSet column_list = select_query(column_statement);
        try {
            while (column_list.next()) {
                if (column_list.getInt(("pk")) == 0) {
                    query_statement.append(column_list.getString("name")).append(",");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (query_statement.charAt(query_statement.length() - 1) == ',') {
            query_statement = new StringBuilder(query_statement.substring(0, query_statement.length() - 1));
        }

        query_statement.append(") VALUES (");
        for (String value : values) {
            query_statement.append("'").append(value).append("'").append(',');
        }
        if (query_statement.charAt(query_statement.length() - 1) == ',') {
            query_statement = new StringBuilder(query_statement.substring(0, query_statement.length() - 1));
        }
        query_statement.append(")");

        insert_query(String.valueOf(query_statement));
    }
}

