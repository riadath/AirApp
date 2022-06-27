package database.repository;

import database.DB;
import database.service.AsObservable;
import datamodel.customer.TicketInfo;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketRepository extends DB {

    static String table_name = "Tickets";
    private final AsObservable asObservable = new AsObservable();

    public TicketRepository() {
        super();
    }

    // returns number of tickets with the same flightId
    public ObservableList<TicketInfo> ticketAsObservableList(int flightId) {
        final String query = "select Tickets.*," +
                " Flight.id as flightId, Flight.source, Flight.destination, Flight.departure, Flight.remainingSeats" +
                " from Tickets" +
                " left join Flight on Tickets.flightId=Flight.id" +
                " where Tickets.flightId=" + flightId;
        return asObservable.ticketToObservable(select_query(query));
    }

    public void insert(String[] values) {
        super.insert(table_name, values);
    }

    public TicketInfo getTicket (int ticketId) {
        final String query = "select * from " + table_name + " where id = " + ticketId;
        ResultSet ticket = super.select_query(query);

        TicketInfo ticketInfo = null;
        try {
            while (ticket.next()) {
                ticketInfo = new TicketInfo(
                        ticket.getInt("id"),
                        ticket.getString("name"),
                        ticket.getString("email"),
                        ticket.getString("countryOfResidence"),
                        ticket.getString("passportNo"),
                        ticket.getInt("seatNumber"),
                        ticket.getBoolean("checkedIn")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketInfo;

    }

    public boolean getCheckedIn (int ticketId) {
        final String query = "select checkedIn from " + table_name + " where id =" + ticketId;
        ResultSet checkInStatus = super.select_query(query);
        boolean checkInCond = true;
        try {
            while (checkInStatus.next()) {
                checkInCond = checkInStatus.getBoolean("checkedIn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkInCond;
    }

    public ArrayList<Integer> getTendency (String flightClass) {
        final String query = "select id from Tickets where flightClass=" + flightClass;
        return getIntegers(query);
    }

    public ArrayList<Integer> getHistory (String flightId) {
        final String query = "select id from Tickets where flightId=" + flightId;
        return getIntegers(query);
    }

    private ArrayList<Integer> getIntegers(String query) {
        ResultSet rs = select_query(query);
        ArrayList<Integer> ret = new ArrayList<>();

        try {
            while (rs.next()) {
                ret.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void setCheckIn (int ticketId) {
        final String query = "update Tickets set checkedIn=1 where id=" + ticketId;
        super.update_query(query);
    }
}
