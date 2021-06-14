public class Airline {
    public String destination;
    public int flightNumber;
    public String planeType;
    public String departureTime;
    public String[] daysOfweek;

    public Airline(String destination, int flightNumber, String planeType, String departureTime, String[] daysOfweek) {
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.planeType = planeType;
        this.departureTime = departureTime;
        this.daysOfweek = daysOfweek;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "destination=" + destination +
                ", flightNumber='" + flightNumber + '\'' +
                ", planeType=" + planeType +
                ", departureTime=" + departureTime +
                ", daysOfweek=" + daysOfweek +
                '}';
    }
}

