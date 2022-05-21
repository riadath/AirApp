package database.repository;

import database.DB;
import database.service.AsObserbavle;
import datamodel.AirplaneInfo;
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
    private AsObserbavle asObserbavle = new AsObserbavle();

    public AirplaneRepository() {
        super();
    }

    public ObservableList<AirplaneInfo> airplaneAsObservable() {
        return asObserbavle.airplaneToObservable(list(table_name));
    }

    public ObservableList<AirplaneInfo> airplaneAsObservable(String manufacturer) {
        if (manufacturer.length() == 0) return airplaneAsObservable();
        return asObserbavle.airplaneToObservable(list(table_name), manufacturer);
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
