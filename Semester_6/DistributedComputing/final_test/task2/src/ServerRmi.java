import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class ServerRmi {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8080);
            RmiInterface service = new Service();
            registry.rebind("airlines", service);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    static class Service extends UnicastRemoteObject implements RmiInterface {
        DAO repository = new DAO();

        Service() throws RemoteException {
            super();
        }

        @Override
        public List<Airline> getAirlinesByDestination(String destionation) {
            return repository.getAirlinesByDestination(destionation);
        }

        @Override
        public List<Airline> getAirlinesByDayOfWeek(String dayOfWeek) {
            return repository.getAirlinesByDayOfWeek(dayOfWeek);
        }

        @Override
        public List<Airline> getAirlinesByTime(String dayOfWeek, Date departureTime) {
            return repository.getAirlinesByTime(dayOfWeek, departureTime);
        }
    }
}
