package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> {

    private int size;
    private int modCount = 0;
    private SimpleLinkedList.Node<E> first;

    /**
     * Add new element in the beginning of the list.
     */
    public void add(E date) {
        SimpleLinkedList.Node<E> newLink = new SimpleLinkedList.Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
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
        SimpleLinkedList.Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Single node class.
     */
    private static class Node<E> {

        E date;
        SimpleLinkedList.Node<E> next;

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