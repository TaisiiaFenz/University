import java.util.concurrent.Semaphore;

public class BarberShop implements Runnable{
    private Semaphore waitingChairs = new Semaphore(5);
    private Semaphore barberChair = new Semaphore(1);
    Barber barber;

    public void run() {
        barber = new Barber(barberChair);
        new Thread(barber).start();

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Customer customer = new Customer(i, waitingChairs, barberChair, barber);
            new Thread(customer).start();
        }
    }
}
