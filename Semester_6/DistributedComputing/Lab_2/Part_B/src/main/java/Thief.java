import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Thief extends Thread {
    private AtomicBoolean isWorking;
    AtomicBoolean loaderWorking;
    ItemQueue queue;
    private Random random = new Random(System.currentTimeMillis());
    private int stolenItems = 0;

    Thief(ItemQueue queue, AtomicBoolean isWorking, AtomicBoolean loaderWorking) {
        this.queue = queue;
        this.isWorking = isWorking;
        this.loaderWorking = loaderWorking;
    }

    @Override
    public void run() {
        while (isWorking.get()) {
                    Item newItem = new Item(stolenItems,
                    random.nextInt(50) * 100);
            queue.add(newItem);
            System.out.println("Thief find(create) item");
            stolenItems++;
            System.out.println("Thief put item " + "#" + newItem.getCode() + " : $" + newItem.getPrice());
            if (stolenItems >= Settings.ALL_ELEMENTS) {
                System.out.println("Items have ended!");
                isWorking.set(false);
                loaderWorking.set(false);
            }
            try {
                sleep(Settings.DELAY);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
