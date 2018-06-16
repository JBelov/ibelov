package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Current number of elements in the Map.
     */
    private int size = 0;
    /**
     * Map capacity.
     */
    private int capacity = 4;
    /**
     * Map load factor to increase capacity.
     */
    private float load = 0.75f;
    /**
     * Array of elements.
     */
    private Node<K, V>[] table = new Node[capacity];
    /**
     * Map modification counter.
     */
    private int modCount = 0;

    /**
     * Map iterator.
     *
     * @return
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int position = 0;
            private int itrModCount = modCount;

            @Override
            public boolean hasNext() {
                if (itrModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int index = position; index < capacity; index++) {
                    if (table[index] != null) {
                        position = index;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Object next() {
                if (itrModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (hasNext()) {
                    return table[position++].value;
                }
                return null;
            }
        };
    }

    /**
     * Internal node class with its Key and Value.
     *
     * @param <K> Key class.
     * @param <V> Value Class.
     */
    static class Node<K, V> {
        final K key;
        V value;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    /**
     * Checks if the bucket with some key is not empty.
     *
     * @param key key to check.
     * @return true in case it is not empty.
     */
    private boolean notNull(K key) {
        return table[key.hashCode() & (capacity - 1)] != null;
    }

    /**
     * Insert new element into the Map.
     *
     * @param key   key of new element.
     * @param value value of new element.
     * @return true in case of successful insertion. False if element with the same key is already exist.
     */
    boolean insert(K key, V value) {
        if (notNull(key) && table[key.hashCode() & (capacity - 1)].key.equals(key)) {
            return false;
        }
        while (table[key.hashCode() & (capacity - 1)] != null || size >= load * capacity) {
            resize();
        }
        table[key.hashCode() & (capacity - 1)] = new Node<>(key, value);
        size++;
        modCount++;
        return true;
    }

    /**
     * Get the element by its key.
     */
    V get(K key) {
        if (notNull(key) && table[key.hashCode() & (capacity - 1)].key.equals(key)) {
            return table[key.hashCode() & (capacity - 1)].value;
        }
        return null;
    }

    /**
     * Delete the element by its key.
     *
     * @param key key of element to delete.
     * @return true in case of successful deletion.
     */
    boolean delete(K key) {
        if (notNull(key) && table[key.hashCode() & (capacity - 1)].key == key) {
            table[key.hashCode() & (capacity - 1)] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }

    /**
     * Twice extend the capacity of the Map.
     */
    private void resize() {
        capacity = capacity * 2;
        Node[] temp = new Node[capacity];
        for (Node node : table) {
            if (node != null) {
                temp[node.key.hashCode() & (capacity - 1)] = node;
            }
        }
        table = temp;
    }
}



