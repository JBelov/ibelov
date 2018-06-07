package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleLinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    /**
     * Add new element in the beginning of the list.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        if (this.size > 0) {
            this.first.previous = newLink;
        }
        newLink.next = this.first;
        this.first = newLink;
        if (this.size < 1) {
            this.last = newLink;
        }
        this.size++;
        this.modCount++;
    }

    /**
     * Get element by its index.
     */
    public E get(int index) {
        if (index >= this.size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Return size of list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Return the last added element and delete it from list.
     */
    public E deleteLast() {
        if (this.size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        this.first = this.first.next;
        this.size--;
        return result.date;
    }

    /**
     * Return the first added element and delete it from list.
     */
    public E deleteFirst() {
        if (this.size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.last;
        this.last = this.last.previous;
        this.size--;
        return result.date;
    }

    /**
     * Single node class.
     */
    private static class Node<E> {

        E date;
        Node<E> next;
        Node<E> previous;

        Node(E date) {
            this.date = date;
        }
    }

    /**
     * Return iterator.
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
                return index < size && get(index) != null;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (index < size) {
                    return get(index++);
                }
                throw new NoSuchElementException();
            }
        };
    }
}