package ru.job4j.list;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleQueue<E> extends SimpleLinkedList {

    public void push(E element) {
        super.add(element);
    }

    public E poll() {
        return (E) super.deleteFirst();
    }
}