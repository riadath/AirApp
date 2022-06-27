package database.repository;

import database.DB;
import database.service.AsObservable;
import datamodel.AirplaneInfo;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AirplaneRepository extends DB {

    static String table_name = "Airplane";
    private AsObservable asObservable = new AsObservable();

    public AirplaneRepository() {
        super();
    }

    public ObservableList<AirplaneInfo> airplaneAsObservable() {
        return asObservable.airplaneToObservable(list(table_name));
    }

    public ObservableList<AirplaneInfo> filterAirplaneAsObservable () {
        return asObservable.filterAirplaneToObservable(list(table_name));
    }

    public ObservableList<AirplaneInfo> airplaneAsObservable(String manufacturer) {
        if (manufacturer.length() == 0) return airplaneAsObservable();
        return asObservable.airplaneToObservable(list(table_name), manufacturer);
    }

    public String[] getCompanyAsString() {
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

    public String[] getColAsString(String col) {
        return super.getColAsString(col, table_name);
    }

    public void insert(String[] values) {
        super.insert(table_name, values);
    }
}
