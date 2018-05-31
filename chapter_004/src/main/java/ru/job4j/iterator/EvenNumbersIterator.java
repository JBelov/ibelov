package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class EvenNumbersIterator implements Iterator {

    /**
     * Input values array.
     */
    private final int[] values;

    /**
     * Iterator index.
     */
    private int index;

    /**
     * Default constructor.
     *
     * @param values input array.
     */
    public EvenNumbersIterator(int[] values) {
        this.values = values;
        index = getNextIndexFrom(0);
    }

    /**
     * This method provides next valid index if it exist.
     *
     * @param value index start element.
     * @return next valid index, or int -1 if no next element.
     */
    private int getNextIndexFrom(int value) {
        if (value < values.length) {
            for (int index2 = value; index2 < values.length; index2++) {
                if (values[index2] % 2 == 0) {
                    return index2;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        int result = values[index];
        index = getNextIndexFrom(index + 1);
        return result;
    }
}
