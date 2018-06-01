package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> outer) {
        return new Iterator<Integer>() {

            Iterator<Integer> inner = outer.next();

            @Override
            public boolean hasNext() {
                if (inner.hasNext()) {
                    return true;
                }
                while (outer.hasNext()) {
                    inner = outer.next();
                    if (inner.hasNext()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (this.hasNext()) {
                    return inner.next();
                }
                throw new NoSuchElementException();
            }
        };
    }
}