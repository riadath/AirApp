package datamodel.customer;

import java.util.ArrayList;

public interface CustomerService {

    ArrayList<Integer> getHistory(String flight);
    ArrayList<Integer> getTendency(String flightClass);
    boolean verifyUser(String name, String passport);

}
