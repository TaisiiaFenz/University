import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private Vehicle truck;
    private Stock stock;
    private volatile Queue<Integer> buffer;
    private Thief ivanov;
    private Accountant necheporchuk;
    private Loader petrov;
    private volatile Util switcher;
    private final int numberOfThings = 5;

    public static void main(String[] args) {
        Main mainClass = new Main();

        mainClass.start();
    }

    void start() {
        truck = new Vehicle();
        stock = new Stock(numberOfThings);
        buffer = new LinkedList<>();
        switcher = new Util();
        necheporchuk = new Accountant(buffer, switcher, this);
        petrov = new Loader(buffer, truck, switcher, necheporchuk);
        ivanov = new Thief(stock, buffer, petrov);

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("End of operation.");
    }
}
