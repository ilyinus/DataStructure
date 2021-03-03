package lesson8.homework08;

import lesson8.HashTable;

public class HashTableChainImpl<K, V> implements HashTable<K, V> {

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private Item<K, V> next;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Item{" +
                    "key=" + key +
                    ", value=" + value +
                    '}');

            Item<K, V> cur = getNext();

            while (cur != null) {
                sb.append(" -> ").append("Item{" + "key=").append(cur.key).append(", value=").append(cur.value).append('}');
                cur = cur.getNext();
            }

            return sb.toString();
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        public Item<K, V> getNext() {
            return next;
        }

        public void setNext(Item<K, V> next) {
            this.next = next;
        }

    }

    private final Item<K, V>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTableChainImpl(int maxSize) {
        this.data = new Item[maxSize];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);

        if (data[index] != null) {

            if (data[index].getKey().equals(key)) {
                data[index].setValue(value);
                return true;
            }

            Item<K, V> curNode = data[index];

            while (curNode.getNext() != null) {
                curNode = curNode.getNext();
            }

            curNode.setNext(new Item<>(key, value));
            size++;
            return true;

        }

        data[index] = new Item<>(key, value);
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);

        Item<K, V> item = data[index];

        while (item != null) {
            if (item.getKey().equals(key))
                return item.getValue();
            else
                item = item.getNext();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);

        Item<K, V> prev = null;
        Item<K, V> item = data[index];

        while (item != null) {
            if (item.getKey().equals(key)) {
                if (prev == null) {
                    data[index] = item.getNext();
                } else {
                    prev.setNext(item.getNext());
                }
                size--;
                item.setNext(null);
                return item.getValue();
            } else {
                prev = item;
                item = item.getNext();
            }
        }

        return null;
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
    public void display() {
        System.out.println("----------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]", i, data[i]);
            System.out.println();
        }
        System.out.println("----------");
    }

}
