import java.rmi.Remote;
import java.util.Date;
import java.util.List;

public interface RmiInterface extends Remote {

    List<Airline> getAirlinesByDestination(String destionation);

    List<Airline> getAirlinesByDayOfWeek(String dayOfWeek);

    List<Airline> getAirlinesByTime(String dayOfWeek, Date departureTime);
}
