public class Main {
    public static void main(String[] args) {
        LockFreeSkipList<Integer> list = new LockFreeSkipList<>();
        Integer a = 4, b = 2, c = 3, d = 6;
        list.add(a);
        list.add(b);
        list.add(c);
        list.remove(a);
        System.out.println(list.contains(a));
    }
}
