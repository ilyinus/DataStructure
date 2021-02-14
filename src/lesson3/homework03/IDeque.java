package lesson3.homework03;

import lesson3.queue.IQueue;

public interface IDeque<E> extends IQueue<E> {

    boolean addFirst(E value);

    void addLast(E value);

    E pollFirst();

    E pollLast();

}
