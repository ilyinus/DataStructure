package lesson2;

import java.util.ArrayList;
import java.util.Collections;

public class TestDynamicArray {
    private final static int CAPACITY = 100_000;

    public static void main(String[] args) {

        Array<Integer> data1 = new ArrayImpl<>(CAPACITY);
        Array<Integer> data2 = new ArrayImpl<>(CAPACITY);
        Array<Integer> data3 = new ArrayImpl<>(CAPACITY);
        Array<Integer> data4 = new ArrayImpl<>(CAPACITY);

        for (int i = 0; i < CAPACITY; i++) {
            int rnd = (int) (Math.random() * 1000);
            data1.add(rnd);
            data2.add(rnd);
            data3.add(rnd);
            data4.add(rnd);
        }

        long begin = 0L;

        System.out.println("Сортировка пузырьком");
        System.out.println("--------------------");
        begin = System.currentTimeMillis();
        data1.sortBubble();
        System.out.println("Время сортировки: " + (System.currentTimeMillis() - begin) + " мс.");
        System.out.println();

        System.out.println("Сортировка выбором");
        System.out.println("------------------");
        begin = System.currentTimeMillis();
        data2.sortSelect();
        System.out.println("Время сортировки: " + (System.currentTimeMillis() - begin) + " мс.");
        System.out.println();

        System.out.println("Сортировка вставкой");
        System.out.println("-------------------");
        begin = System.currentTimeMillis();
        data3.sortInsert();
        System.out.println("Время сортировки: " + (System.currentTimeMillis() - begin) + " мс.");
        System.out.println();

        System.out.println("Быстрая сортировка");
        System.out.println("------------------");
        begin = System.currentTimeMillis();
        data4.quickSort(0, data4.size() - 1);
        System.out.println("Время сортировки: " + (System.currentTimeMillis() - begin) + " мс.");
        System.out.println();

        boolean order = true;
        for (int i = 0; i < CAPACITY; i++) {
            if (!data1.get(i).equals(data2.get(i)) ||
                    !data1.get(i).equals(data3.get(i)) ||
                    !data1.get(i).equals(data4.get(i))) {
                order = false;
                break;
            }
        }

        if (!order)
            System.out.println("Результирующие наборы имеют разный порядок");
        else
            System.out.println("Наборы имеют одинаковый порядок");

    }
}
