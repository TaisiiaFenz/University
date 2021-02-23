public class Util {
    private volatile boolean isLastElementCounted;

    synchronized boolean getIsLastElementCounted() {
        return isLastElementCounted;
    }

    synchronized void setIsLastElementCounted(boolean value) {
        isLastElementCounted = value;
    }
}
