package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleList<E> implements Iterable<E> {

    /**
     * Creating the main container.
     */
    private Object[] container;
    /**
     * Initializing the current position pointer.
     */
    private int position = 0;
    /**
     * Initializing the modification counter.
     */
    private int modCount = 0;

    /**
     * Class constructor.
     *
     * @param size initial size of container.
     */
    public SimpleList(int size) {
        this.container = new Object[size];
    }

    /**
     * Add a new element to container. If container is full then increase its capacity for 0.5.
     *
     * @param value element to add.
     */
    public void add(E value) {
        if (position >= this.container.length) {
            Object[] temp = new Object[container.length + container.length / 2];
            System.arraycopy(container, 0, temp, 0, container.length);
            container = temp;
        }
        container[position++] = value;
        modCount++;
    }

    /**
     * Get the element by its index.
     *
     * @param index index of element.
     * @return Element by its index.
     */
    public E get(int index) {
        if (index >= position) {
            throw new NoSuchElementException();
        }
        return (E) this.container[index];
    }

    /**
     * Iterator for the SimpleList.
     *
     * @return iterator.
     */
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < container.length && container[index] != null;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (this.hasNext()) {
                    return (E) container[index++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
