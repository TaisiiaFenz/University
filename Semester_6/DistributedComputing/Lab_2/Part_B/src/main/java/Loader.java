import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Loader implements Runnable{
    private Thread thread;
    private volatile Queue<Integer> buffer;
    private Accountant nechypurenko;
    private volatile Vehicle vehicle;
    private volatile boolean isRunning;
    private volatile Util switcher;

    Loader(Queue<Integer> buffer, Vehicle vehicle, Util switcher,Accountant nechypurenko) {
        this.buffer = buffer;
        this.vehicle = vehicle;
        this.switcher = switcher;
        this.nechypurenko = nechypurenko;
        thread = new Thread(this, "Petrov");
        isRunning = true;
        thread.start();
    }

    public void run() {
        while (isRunning) {
            synchronized (buffer) {
                if (!buffer.isEmpty() && switcher.getIsLastElementCounted()) {
                    vehicle.addElement(buffer.poll());
                    switcher.setIsLastElementCounted(false);
                }
            }
        }
        int elementsLeft;
        synchronized (buffer) {
            elementsLeft = buffer.size();
        }
        while (elementsLeft > 0) {
            if (switcher.getIsLastElementCounted()) {
                synchronized (buffer) {
                    vehicle.addElement(buffer.poll());
                    elementsLeft = buffer.size();
                    System.out.println("Petrenko add item to car");
                }
                switcher.setIsLastElementCounted(false);
            }

        }
        nechypurenko.stop();
    }

    void stop() {
        isRunning = false;
    }
}
