import org.junit.Test;
import static org.junit.Assert.*;

public class LockFreeSkipListTest {

    LockFreeSkipList<Integer> list = new LockFreeSkipList<>();

    @Test
    public void createLockFreeSkipList() {
        assertNotNull(list);
    }

    @Test
    public void addItemToLockFreeSkipList() {
        Integer a = 4, b = 2, c = 3, d = 6;
        list.add(a);
        list.add(b);
        list.add(c);
        assertTrue(list.contains(a));
        assertTrue(list.contains(b));
        assertTrue(list.contains(c));
        assertEquals(list.contains(d), false);
    }

    @Test
    public void removeItemToLockFreeSkipList() {
        Integer a = 4, b = 2;
        list.add(a);
        list.add(b);
        assertEquals(list.contains(a), true);
        list.remove(a);
        assertEquals(list.contains(a), false);
    }

    public Thread insertionThread(int element) {
        Thread thread = new Thread(() -> {
            list.add(element);
        });
        thread.start();
        return thread;
    }

    public Thread removingThread(int element) {
        Thread thread = new Thread(() -> {
            list.remove(element);
        });
        thread.start();
        return thread;
    }

    @Test
    public void addItemThreadToLockFreeSkipList() throws InterruptedException {
        Integer a = 4, b = 2, c = 3;
        insertionThread(a).join();
        insertionThread(b).join();
        insertionThread(c).join();

        assertTrue(list.contains(a));
        assertTrue(list.contains(b));
        assertTrue(list.contains(c));
    }

    @Test
    public void removeItemThreadToLockFreeSkipList() throws InterruptedException {
        Integer a = 4, b = 2, c = 3;
        insertionThread(a).join();
        insertionThread(b).join();
        insertionThread(c).join();

        removingThread(a).join();

        assertFalse(list.contains(a));
        assertTrue(list.contains(b));
        assertTrue(list.contains(c));
    }
}
