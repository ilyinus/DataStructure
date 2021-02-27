package lesson4.homework4;

public interface MyList<E> extends Iterable<E> {

    void add(E value);

    void remove(E value);

    E get(int index);

    int size();

    boolean isEmpty();

}
