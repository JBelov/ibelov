package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */


public class MatrixIterator implements Iterator {

    /**
     * Input values array.
     */
    private final int[][] values;

    /**
     * Iterator index.
     */
    private int index = 0;

    /**
     * Array's outer index.
     */
    private int outerIndex = 0;

    /**
     * Array's inner index.
     */
    private int innerIndex = 0;

    /**
     * Array'a absolute length.
     */
    private int length = 0;

    /**
     * Main constructor with absolute array length calculation.
     *
     * @param values input array.
     */
    public MatrixIterator(final int[][] values) {
        this.values = values;
        for (int[] inner : values
                ) {
            length = length + inner.length;
        }
    }

    @Override
    public boolean hasNext() {
        return length > index;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (innerIndex == values[outerIndex].length) {
            innerIndex = 0;
            outerIndex++;
        }
        index++;
        return values[outerIndex][innerIndex++];
    }
}
