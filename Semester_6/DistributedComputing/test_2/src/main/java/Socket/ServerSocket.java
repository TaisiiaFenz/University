package Socket;

import dao.CatalogDaoImpl;
import dao.Connector;
import dao.FileDaoImpl;
import models.Catalog;
import models.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.ArrayList;

public class ServerSocket {
    protected int serverPort;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    private final CatalogDaoImpl catalogRepository = new CatalogDaoImpl(Connector.getConnection());
    private final FileDaoImpl fileRepository = new FileDaoImpl(Connector.getConnection());

    private PrintWriter out;
    private BufferedReader in;

    public ServerSocket(int port) {
        this.serverPort = port;
        serverSocket = new ServerSocket(serverPort);
    }

    public static void main(String[] args) {
        ServerSocket socket = new ServerSocket(8080);
        socket.run();
    }

    public void run() {
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }

            String msg = null;
            try {
                msg = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Received message " + msg);

            executeDao(parseMessage(msg));
        }
        System.out.println("Server Stopped.");
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    private void executeDao(ArrayList<String> args) {
        switch (args.get(0)) {
            case "1": out.println(catalogRepository.findAll());
            case "2": out.println(fileRepository.findAll());
            case "3": out.println(catalogRepository.save(new Catalog(args.get(1), args.get(2))));
            case "4": out.println(fileRepository.save(new File(args.get(1), args.get(2), Integer.parseInt(args.get(3)))));
            case "5": out.println(catalogRepository.deleteById(Integer.parseInt(args.get(1))));
            case "6": out.println(fileRepository.deleteById(Integer.parseInt(args.get(1))));
        }
        out.flush();
    }

    private ArrayList<String> parseMessage(String str) {
        ArrayList<String> list = new ArrayList<>();
        String[] array = str.split(";");
        list.add(array[0]);
        for (int i = 1; i < array.length; i++) {
            String[] split = array[i].split("=");
            list.add(split[1]);
        }
        return list;
    }
}
