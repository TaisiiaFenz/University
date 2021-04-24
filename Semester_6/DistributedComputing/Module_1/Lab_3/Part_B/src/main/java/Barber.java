import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Barber implements Runnable {
    private AtomicBoolean isSleeping = new AtomicBoolean(true);
    private Semaphore barberChair;

    Barber(Semaphore barberChair) {
        this.barberChair = barberChair;
    }

    @Override
    public void run() {
        while (true) {
            if (isSleeping.get()) {
                System.out.println("Barber is sleeping... zzzz...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (barberChair.tryAcquire()) {
                    isSleeping.set(true);
                } else {
                    System.out.println("The barber is cutting hair");
                    barberChair.release();
                }
            }
        }
    }

    void wakeUp() {
        if (isSleeping.get()) {
            isSleeping.set(false);
            barberChair.release();
            System.out.println("The barber is woken up");
        }
    }
}
