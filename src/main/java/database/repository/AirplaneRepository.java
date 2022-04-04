package database.repository;

import database.DB;
import datamodel.FlightInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AirplaneRepository extends DB {

    static String table_name = "Airplane";

    public AirplaneRepository() {super();}

    public ObservableList<FlightInfo> airplaneAsObservable () {
        ResultSet rs = list(table_name);
        ObservableList<FlightInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                ret.add( new FlightInfo(rs.getInt("id"), rs.getString("name"),
                        rs.getString("code"), rs.getInt("no_of_seats")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public  ObservableList<FlightInfo> airplaneAsObservable (String manufacturer) {
        if (manufacturer.length() == 0) return airplaneAsObservable();
        ResultSet rs = list(table_name);
        ObservableList<FlightInfo> ret = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                if (rs.getString("name").contains(manufacturer)){
                    ret.add( new FlightInfo(rs.getInt("id"), rs.getString("name"),
                            rs.getString("code"), rs.getInt("no_of_seats")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public String[] getCompanyAsStringArr () {
        final String query = "SELECT name FROM " + table_name;
        ResultSet rs = select_query(query);
        Set<String> ret = new HashSet<>();
        try {
            while (rs.next()) {
                ret.add(rs.getString("name").split(" ")[0]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] retString = new String[ret.size()];
        return ret.toArray(retString);
    }

    public void insert (String[] values) {super.insert(table_name, values);}
}
