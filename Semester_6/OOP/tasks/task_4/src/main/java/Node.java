import java.util.concurrent.atomic.AtomicMarkableReference;

public final class Node<T> {
    final T value;
    final int key;

    final AtomicMarkableReference<Node<T>>[] next;
    public int topLevel;
    public Node(int key) {
        this.value = null;
        this.key = key;
        //System.out.println(this.key);
        this.next = (AtomicMarkableReference<Node<T>>[])new AtomicMarkableReference[LockFreeSkipList.MAX_LEVEL + 1];
        this.topLevel = LockFreeSkipList.MAX_LEVEL;
        //System.out.println(next.length);
        for (int i = 0; i < next.length; i++) {
            next[i] = new AtomicMarkableReference<Node<T>>(null,false);
        }
    }
    public Node(T x, int height) {
        this.value = null;
        this.key = x.hashCode();
        this.next = (AtomicMarkableReference<Node<T>>[])new AtomicMarkableReference[LockFreeSkipList.MAX_LEVEL + 1];
        this.topLevel = LockFreeSkipList.MAX_LEVEL;
        for (int i = 0; i < next.length; i++) {
            next[i] = new AtomicMarkableReference<Node<T>>(null,false);
        }
    }

}
