package lesson3.homework03;

import lesson3.queue.QueueImpl;

public class IDequeImpl<E> extends QueueImpl<E> implements IDeque<E> {
    public IDequeImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean addFirst(E value) {
        if (isFull()) {
            return false;
        }

        if (head == 0) {
            head = data.length - 1;
        }

        data[head] = value;
        size++;
        return true;
    }

    @Override
    public void addLast(E value) {
        super.insert(value);
    }

    @Override
    public E pollFirst() {
        return super.remove();
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }

        E removedValue = data[tail];
        data[tail--] = null;
        size--;
        return removedValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < data.length - 1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(data[data.length - 1]);
        }
        sb.append("]");

        return sb.toString();
    }

}
