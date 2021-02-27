package lesson4.homework4;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        MyList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        System.out.println(list);
        list.remove(3);
        list.remove(4);
        list.add(10);
        System.out.println(list);
        System.out.println(list.get(7));

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value.equals(8) || value.equals(10)) {
                iterator.remove();
            }
        }

        for (Integer item : list) {
            System.out.println(item);
        }

    }

}
