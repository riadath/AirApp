package datamodel;

public class Luggage {
    private int mass;

    public Luggage (int mass) {
        this.mass = mass;
    }

    public boolean checkAcceptableMass () {
        return mass < 36;
    }
}
