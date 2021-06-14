import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Scanner;

public class ClientRmi {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            RmiInterface st = (RmiInterface) Naming.lookup("rmi://localhost:8080/airlines");

            System.out.println("1. getAirlinesByDestination \n" +
                    "2. getAirlinesByDayOfWeek \n" +
                    "3. getAirlinesByTime  \n" + "Select:");
            int variant = s.nextInt();

            switch (variant) {
                case 1 -> {
                    System.out.println("Input destionation:");
                    String destionation = s.next();
                    System.out.println(st.getAirlinesByDestination(destionation));
                }
                case 2 -> {
                    System.out.println("Input dayOfWeek:");
                    String dayOfWeek = s.next();
                    System.out.println(st.getAirlinesByDayOfWeek(dayOfWeek));
                }
                case 3 -> {
                    System.out.println("Input dayOfWeek:");
                    String dayOfWeek = s.next();
                    System.out.println(st.getAirlinesByTime(dayOfWeek, new Date()));
                }
                default -> {
                    return;
                }
            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
