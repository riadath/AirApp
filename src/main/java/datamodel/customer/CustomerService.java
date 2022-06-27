package datamodel.customer;

import java.util.ArrayList;

public interface CustomerService {

    public ArrayList<Integer> getHistory (String flight);
    public ArrayList<Integer> getTendency (String flightClass);

}
