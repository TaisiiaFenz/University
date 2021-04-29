package RMI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            RMIInterface st = (RMIInterface) Naming.lookup("rmi://localhost:8080/file-system");

            System.out.println("1. Get all catalogs \n" +
                    "2. Get all files  \n" +
                    "3. Create new catalog  \n" +
                    "4. Create new file  \n" +
                    "5. Delete Catalog by id  \n " +
                    "6. Delete File by id  \n Select:");
            int variant = s.nextInt();

            switch (variant) {
                case 1: {
                    System.out.println(st.getAllCategories());
                }
                case 2: {
                    System.out.println(st.getAllFiles());
                }
                case 3: {
                    System.out.println("Input name:");
                    String name = s.next();
                    System.out.println("Input catalog:");
                    String catalog = s.next();
                    System.out.println(st.createCategory(name, catalog));
                }
                case 4: {
                    System.out.println("Input name:");
                    String name = s.next();
                    System.out.println("Input catalog:");
                    String catalog = s.next();
                    System.out.println("Input size:");
                    int size = s.nextInt();
                    System.out.println(st.createFile(name, catalog, size));
                }
                case 5: {
                    System.out.println("Input id:");
                    int id = s.nextInt();
                    System.out.println(st.deleteCategoryById(id));
                }
                case 6: {
                    System.out.println("Input id:");
                    int id = s.nextInt();
                    System.out.println(st.deleteFileById(id));
                }
                default: {
                    return;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}