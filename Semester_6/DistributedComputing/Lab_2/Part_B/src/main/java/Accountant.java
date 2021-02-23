import java.util.Queue;

public class Accountant implements Runnable {
    private Thread thread;
    private int totalPrice;
    private volatile Queue<Integer> buffer;
    private volatile boolean isRunning;
    private volatile Util switcher;
    private Main mainClass;

    Accountant(Queue<Integer> buffer, Util switcher, Main mainClass) {
        totalPrice = 0;
        this.buffer = buffer;
        isRunning = true;
        this.switcher = switcher;
        this.mainClass = mainClass;
        thread = new Thread(this, "Necheporchuk");
        thread.start();
    }

    public void run() {
        while (isRunning) {
            synchronized (buffer) {
                if (!buffer.isEmpty() && !switcher.getIsLastElementCounted()) {
                    totalPrice += buffer.peek();
                    switcher.setIsLastElementCounted(true);
                    System.out.println("Nechypurenko count item");
                }

            }
        }
        System.out.println("Total price: " + totalPrice);
        synchronized (mainClass) {
            mainClass.notify();
        }
    }

    void stop() {
        isRunning = false;
    }
}
