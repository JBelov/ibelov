package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleLinkedSet<E> extends SimpleLinkedList<E> {

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

