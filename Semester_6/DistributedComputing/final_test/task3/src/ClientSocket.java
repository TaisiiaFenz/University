import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) throws IOException {
        startConnection("localhost", 8080);

        Scanner s = new Scanner(System.in);

        System.out.println("1. getAirlinesByDestination \n" +
                "2. getAirlinesByDayOfWeek \n" +
                "3. getAirlinesByTime  \n" + "Select:");
        int variant = s.nextInt();

        switch (variant) {
            case 1 -> {
                System.out.println("Input destionation:");
                String destionation = s.next();
                sendMessage(variant + ";destionation=" + destionation);
            }
            case 2 -> {
                System.out.println("Input DayOfWeek:");
                String DayOfWeek = s.next();
                sendMessage(variant + ";DayOfWeek=" + DayOfWeek);
            }
            case 3 -> {
                System.out.println("Input dayOfWeek:");
                String dayOfWeek = s.next();
                System.out.println("Input departureTime:");
                String departureTime = s.next();
                sendMessage(variant + ";dayOfWeek=" + dayOfWeek + ";departureTime=" + departureTime);
            }
            default -> {
                return;
            }
        }
        try {
            stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public static void sendMessage(String msg) throws IOException {
        out.println(msg);
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
