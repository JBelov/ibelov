package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
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
    public PrimeIterator(int[] values) {
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
                int num = values[value];
                boolean isPrime = true;
                for (int i = 2; i <= num / 2; i++) {
                    if (num % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
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
