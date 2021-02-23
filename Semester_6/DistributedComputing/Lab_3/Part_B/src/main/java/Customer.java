import java.util.concurrent.Semaphore;

public class Customer implements Runnable{
    private long id;
    private Semaphore waitingChairs;
    private Semaphore barberChair;
    Barber barber;

    Customer(long id, Semaphore waitingChairs, Semaphore barberChair, Barber barber) {
        this.id = id;
        this.waitingChairs = waitingChairs;
        this.barberChair = barberChair;
        this.barber = barber;
    }

    @Override
    public void run() {
        if (waitingChairs.tryAcquire()) {
            System.out.println("Client " + id + " waiting");
            while (!barberChair.tryAcquire()) {
                barber.wakeUp();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Client " + id + " set in barber chair");
            waitingChairs.release();
        } else {
            System.out.println("No free sits");
        }
    }
}
