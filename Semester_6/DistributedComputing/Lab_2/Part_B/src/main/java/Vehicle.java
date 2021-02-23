import java.util.LinkedList;
import java.util.List;

public class Vehicle {
    private List<Integer> list;


    public Vehicle() {
        this.list = new LinkedList<>();
    }

    synchronized void addElement(Integer element) {
        list.add(element);
    }
}
