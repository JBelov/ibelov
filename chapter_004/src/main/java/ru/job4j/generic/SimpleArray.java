package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleArray<T> implements Iterable<T> {

    /**
     * Objects array and position pointer.
     */
    private final Object[] objects;
    private int position = 0;

    /**
     * SimpleArray constructor.
     *
     * @param size array size.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Check if the element with some index exist.
     *
     * @param index index of the element to be checked.
     * @throws IllegalStateException when no element with this index.
     */
    public void check(int index) {
        if (index >= position) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Adding new element in array.
     *
     * @param model incoming element.
     * @throws IllegalStateException when the array is full.
     */
    public void add(T model) throws IllegalStateException {
        if (position == objects.length) {
            throw new IllegalStateException();
        }
        this.objects[position++] = model;
    }

    /**
     * Set new element for current index.
     *
     * @param index index of the element to be replaced.
     * @param model new element.
     */
    public void set(int index, T model) {
        check(index);
        this.objects[index] = model;
    }

    /**
     * Remove an element and shift the remaining elements to the left.
     *
     * @param index index of element to remove.
     */
    public void delete(int index) {
        check(index);
        System.arraycopy(objects, index + 1, objects, index, objects.length - index - 1);
        position--;
    }

    /**
     * Get the element by it's index.
     *
     * @param index index of element.
     * @return element from this index.
     */
    public T get(int index) {
        check(index);
        return (T) this.objects[index];
    }

    /**
     * Create new iterator for the SimpleArray.
     *
     * @return Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        T[] objects = (T[]) this.objects;
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < objects.length && objects[index] != null;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (this.hasNext()) {
                    return objects[index++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
