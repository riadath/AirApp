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

    public void insert_query_alt (String name, String code, Integer no_of_seats) {
        try {
            final String query = "INSERT into Airplane (name, code, no_of_seats) VALUES (?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, no_of_seats);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

