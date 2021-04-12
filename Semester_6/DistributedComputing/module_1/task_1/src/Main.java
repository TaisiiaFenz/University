import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final class PaymentTerm {
        public final int turnPurchase;
        public final int userId;

        public PaymentTerm(int turnPurchase, int userId) {
            this.turnPurchase = turnPurchase;
            this.userId = userId;
        }
    }

    private static final int THREAD_COUNT = 7;
    private static final int RESULT_LOT = 4;

    private static ReentrantLock paymentsLock = new ReentrantLock();
    private static ArrayList<PaymentTerm> paymentTerm = new ArrayList<>();


    private static int[] skippedTurns = new int[THREAD_COUNT];
    private static int[] proposedPrices = new int[THREAD_COUNT];
    private static int[] nextPrices = new int[THREAD_COUNT];
    private static int[] banTimeout = new int[THREAD_COUNT];

    private static int currentLot;
    private static int currentTurn;

    private static final int BANNED = -1;

    public static void barrier() {
        nextPrices = proposedPrices;

        int[] swapBuffers = nextPrices;
        proposedPrices = swapBuffers;

        for (PaymentTerm PaymentTerm : paymentTerm) {
            if (currentTurn - PaymentTerm.turnPurchase >= 2) {
                banTimeout[PaymentTerm.userId] = 3;
            }
        }

        boolean productSold = true;
        for (int i = 0; i < THREAD_COUNT; i++) {
            if (skippedTurns[i] < 3) {
                productSold = false;
                break;
            }
        }

        if (productSold) {
            int maxPrice = 0, buyer = 0;
            for (int i = 0; i < THREAD_COUNT; i++) {
                if (proposedPrices[i] > maxPrice) {
                    buyer = i;
                    maxPrice  = proposedPrices[i];
                }
            }
            System.out.println("Max price: " + maxPrice);
            System.out.println("Buyer: " + buyer);
            paymentTerm.add(new PaymentTerm(currentTurn, buyer));
            currentLot++;

            for (int i = 0; i < THREAD_COUNT; i++) {
                proposedPrices[i] = 0;
                if (skippedTurns[i] != BANNED)
                    skippedTurns[i] = 0;
            }
            if (currentLot == 3) return;

            System.out.printf("Now selling lot %d\n", currentLot);
        }
        currentTurn++;
    }

    public static void thread(int i, CyclicBarrier barrier) {
        while (currentLot < RESULT_LOT) {

            if (banTimeout[i] > 0) {
                banTimeout[i]--;
                skippedTurns[i] = BANNED;

            } else if (Math.random() < 0.3) {
                int maxPrice = 0;
                for (int proposedPrice : proposedPrices) {
                    if (proposedPrice > maxPrice)
                        maxPrice = proposedPrice;
                }
                nextPrices[i] = maxPrice + 100;
            } else {
                System.out.printf("[User %d] Skip turn\n", i);
                nextPrices[i] = proposedPrices[i];
                skippedTurns[i]++;
            }

            paymentsLock.lock();
            for (int j = 0; j < paymentTerm.size();) {

                if (paymentTerm.get(j).userId == i) {
                    if (Math.random() < 0.5) {
                        System.out.printf("[User %d] I'm paying.\n", i);
                        paymentTerm.remove(j);
                    } else {
                        System.out.printf("[User %d] I'll pay later.\n", i);
                        j++;
                    }
                } else {
                    j++;
                }
            }
            paymentsLock.unlock();

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT, () -> barrier());


        for (int threadNum = 0; threadNum < THREAD_COUNT; threadNum++) {
            int finalThreadNum = threadNum;
            Thread thread = new Thread(() -> thread(finalThreadNum, barrier));
            thread.start();
        }
    }
}
