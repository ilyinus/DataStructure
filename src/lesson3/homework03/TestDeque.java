package lesson3.homework03;

public class TestDeque {

    public static void main(String[] args) {
        IDeque<Integer> iDeque = new IDequeImpl<>(10);
        iDeque.addLast(1);
        iDeque.addLast(2);
        iDeque.addLast(3);
        iDeque.addFirst(4);
        System.out.println(iDeque);
        System.out.println(iDeque.pollFirst());
        System.out.println(iDeque.pollLast());
        iDeque.addLast(5);
        iDeque.addFirst(6);
        System.out.println(iDeque);
    }

}
