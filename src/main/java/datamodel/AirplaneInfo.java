package datamodel;

public class AirplaneInfo {
    private final Integer id;
    private final String name;
    private final String code;
    private final Integer number_of_seats;

    public AirplaneInfo(Integer id, String name, String code, Integer number_of_seats) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.number_of_seats = number_of_seats;
    }

    public Integer getId() { return id;}

    public String getName() { return name;}

    public String getCode() { return code;}

    public Integer getNumber_of_seats() { return number_of_seats;}

    @Override
    public String toString() {
        return "AirplaneInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", number_of_seats=" + number_of_seats +
                '}';
    }
}
