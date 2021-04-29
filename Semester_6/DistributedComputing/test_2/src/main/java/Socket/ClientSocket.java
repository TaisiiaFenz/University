package Socket;

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

        System.out.println("1. Select all souvenirs by producer id \n" +
                "2. Select all souvenirs by country  \n" +
                "3. Select all producers by souvenir price  \n" +
                "4. Select all producers by souvenir name and country  \n" +
                "5. Delete producer by id  \n Select:");
        System.out.println("1. Get all catalogs \n" +
                "2. Get all files  \n" +
                "3. Create new catalog  \n" +
                "4. Create new file  \n" +
                "5. Delete Catalog by id  \n " +
                "6. Delete File by id  \n Select:");
        int variant = s.nextInt();

        switch (variant) {
            case 5: {
                System.out.println("Input id:");
                int id = s.nextInt();
                sendMessage(variant + ";id=" + id);
            }
            case 6: {
                System.out.println("Input id:");
                int id = s.nextInt();
                sendMessage(variant + ";id=" + id);
            }
            case 1: {
                sendMessage("Catalogs: " + variant);
            }
            case 2: {
                sendMessage("Files: " + variant);
            }
            case 3: {
                System.out.println("Input name:");
                String name = s.next();
                System.out.println("Input catalog: ");
                String catalog = s.next();
                sendMessage(variant + ";name=" + name + ";catalog=" + catalog);
            }
            case 4: {
                System.out.println("Input name:");
                String name = s.next();
                System.out.println("Input catalog: ");
                String catalog = s.next();
                System.out.println("Input size: ");
                int size = s.nextInt();
                sendMessage(variant + ";name=" + name + ";catalog=" + catalog + ";size=" + size);
            }
            default: {
                return;
            }
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
