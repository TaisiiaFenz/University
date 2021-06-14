import java.util.Date;

public class Airline {
    public String destination;
    public int flightNumber;
    public String planeType;
    public Date departureTime;
    public String dayOfweek;

    public Airline(String destination, int flightNumber, String planeType, Date departureTime, String daysOfweek) {
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.planeType = planeType;
        this.departureTime = departureTime;
        this.dayOfweek = daysOfweek;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "destination=" + destination +
                ", flightNumber='" + flightNumber + '\'' +
                ", planeType=" + planeType +
                ", departureTime=" + departureTime +
                ", daysOfweek=" + dayOfweek +
                '}';
    }
}

