import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAO {
    List<Airline> airlineList = new ArrayList<>();

    public DAO(List<Airline> airlineList) {
        this.airlineList = airlineList;
        createAirlineList();
    }

    public DAO() { }

    public void createAirlineList() {
        airlineList.add(new Airline("Spain", 1, "type", new Date(), "Monday"));
        airlineList.add(new Airline("Italy", 2, "type", new Date(1212121212121L), "Wednesday"));
        airlineList.add(new Airline("France", 3, "type", new Date(121212121), "Friday"));
    }

    public List<Airline> getAirlinesByDestination(String destionation) {
        List<Airline> airlines = new ArrayList<>();
        for (Airline airline : airlineList) {
            if (airline.destination == destionation) airlines.add(airline);
        }
        return airlines;
    }

    public List<Airline> getAirlinesByDayOfWeek(String dayOfWeek) {
        List<Airline> airlines = new ArrayList<>();
        for (Airline airline : airlineList) {
            if (airline.dayOfweek == dayOfWeek) airlines.add(airline);
        }
        return airlines;
    }

    public List<Airline> getAirlinesByTime(String dayOfWeek, Date departureTime) {
        List<Airline> airlines = new ArrayList<>();
        for (Airline airline : airlineList) {
            if ((airline.dayOfweek == dayOfWeek)&&(departureTime.getTime() > airline.departureTime.getTime())) {
                airlines.add(airline);
            }
        }
        return airlines;
    }
}
