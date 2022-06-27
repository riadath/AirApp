package datamodel.fleet;

import java.util.ArrayList;
import java.util.List;

public class AirFleet {

    static private int airCraftCount = 0;
    static private int pendingFlightCount = 0;

    static private final List<Integer> aircraftList = new ArrayList<>();
    static private final List<Integer> flightList = new ArrayList<>();

    AirFleet () { }

    protected boolean checkAircraftIdInit (int id) {
        return aircraftList.contains(id);
    }

    protected void addAirCraft (int id) {
        aircraftList.add(id);
        airCraftCount = airCraftCount + 1;
    }

    protected void decommissionAirCraft (int id) {
        aircraftList.remove(id);
        airCraftCount = airCraftCount - 1;
    }

    protected boolean checkFlightIdInit (int id) {
        return flightList.contains(id);
    }

    protected void addPendingFlightCount (int id) {
        flightList.add(id);
        pendingFlightCount = pendingFlightCount + 1;
    }

    protected void completeFlight (int id) {
        flightList.remove(id);
        pendingFlightCount = pendingFlightCount - 1;
    }



}
