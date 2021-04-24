import java.util.Queue;

public class Thief implements Runnable {
    private Thread thread;
    private Stock stock;
    private volatile Queue buffer;
    private Loader petrov;

    Thief(Stock stock, Queue<Integer> buffer, Loader petrov) {
        this.stock = stock;
        this.buffer = buffer;
        this.petrov = petrov;
        thread = new Thread(this, "Ivanov");
        thread.start();
    }

    public void run() {
        while (!stock.isEmpty()) {
            synchronized (buffer) {
                buffer.add(stock.getStuff());
                System.out.println("Ivanov get from stock");
            }
        }
        petrov.stop();
    }
}
