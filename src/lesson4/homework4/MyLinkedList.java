package lesson4.homework4;

import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data, Node<E> prev) {
            this.data = data;
            this.prev = prev;
        }
    }

    private static class ListIterator<E> implements Iterator<E> {
        Node<E> current;
        Node<E> lastReturned;

        public ListIterator(MyLinkedList<E> list) {
            this.current = list.head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E value = current.data;
            lastReturned = current;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            if (lastReturned != null) {
                if (lastReturned.prev != null) {
                    lastReturned.prev.next = lastReturned.next;
                }
                if (lastReturned.next != null) {
                    lastReturned.next.prev = lastReturned.prev;
                }
                lastReturned = null;
            }
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(this);
    }

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value,null);

        if (isEmpty()) {
            this.head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }

        this.tail = node;
        size++;

    }

    @Override
    public void remove(E value) {
        Node<E> current = head;

        while (current != null) {
            if (current.data.equals(value)) {

                if (size == 1) {
                    head = null;
                    tail = null;
                } else if (current.prev != null && current.next != null) {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                } else if (current.prev != null) {
                    current.prev.next = null;
                    tail = current.prev;
                }

                size--;
                break;

            } else {
                current = current.next;
            }
        }

    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        Node<E> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    break;
                } else {
                    current = current.next;
                }
            }
        } else {
            current = tail;
            for (int i = size - 1; i >= 0; i--) {
                if (i == index) {
                    break;
                } else {
                    current = current.prev;
                }
            }
        }

        return current.data;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
            if (current != null)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
