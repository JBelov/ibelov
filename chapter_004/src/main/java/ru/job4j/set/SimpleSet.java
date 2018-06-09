package ru.job4j.set;

import ru.job4j.list.SimpleList;

import java.util.Iterator;

public class SimpleSet<E> extends SimpleList<E> {
    /**
     * Class constructor.
     *
     * @param size initial size of container.
     */
    public SimpleSet(int size) {
        super(size);
    }

    /**
     * Adds elements without duplicates.
     *
     * @param e element to add.
     */
    @Override
    public void add(E e) {
        boolean unique = true;
        Iterator itr = super.iterator();
        while (itr.hasNext()) {
            E element = (E) itr.next();
            if (element.equals(e)) {
                unique = false;
                break;
            }
        }
        if (unique) {
            super.add(e);
        }
    }
}
