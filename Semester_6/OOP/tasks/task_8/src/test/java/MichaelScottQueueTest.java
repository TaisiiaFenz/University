import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class MichaelScottQueueTest {
    static Logger log = Logger.getLogger(String.valueOf(MichaelScottQueue.class));
    private MichaelScottQueue<Integer> queue;

    private boolean valuePresent[] = new boolean[5000];

    @Test
    public void enqueueDequeueValuesFromManyThreadsTest() throws InterruptedException {
        queue = new MichaelScottQueue<>();
        enqueueValuesFromManyThreads();
        dequeueValuesFromManyThreads();
        for (int i = 0; i < 5000; i++) {
            Assert.assertTrue(valuePresent[i]);
        }
        Assert.assertNull(queue.dequeue());
    }

    @Test
    public void queueMustContainValuesFromSetTest() {
        queue = new MichaelScottQueue<>();
        CyclicBarrier barrier = new CyclicBarrier(3);
        Set<Integer> set = new HashSet<>();
        set.add(50);
        set.add(100);

        new Thread(() -> {
            queue.enqueue(50);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            queue.enqueue(100);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            log.info((Supplier<String>) e);
        }
        Assert.assertTrue(set.contains(queue.dequeue()));
        Assert.assertTrue(set.contains(queue.dequeue()));
        Assert.assertFalse(set.contains(queue.dequeue()));
    }

    public void enqueueValuesFromManyThreads() throws InterruptedException {

        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j;
            int rightBound = 500 * (j + 1);
            threads[j] = new Thread(() -> {
                for (int i = leftBound; i < rightBound; i++) {
                    queue.enqueue(i);
                }
            });
            threads[j].start();
        }
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }
    }

    public void dequeueValuesFromManyThreads() throws InterruptedException {

        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j;
            int rightBound = 500 * (j + 1);
            threads[j] = new Thread(() -> {
                int current;
                for (int i = leftBound; i < rightBound; i++) {
                    current = queue.dequeue();
                    valuePresent[current] = true;
                }
            });
            threads[j].start();
        }
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }
    }
}
