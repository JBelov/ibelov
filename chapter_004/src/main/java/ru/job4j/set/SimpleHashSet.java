package ru.job4j.set;

import java.util.Objects;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleHashSet<E> {

    /**
     * Values container.
     */
    private Object[] values;
    /**
     * Number of buckets.
     */
    private int buckets;
    /**
     * Current number of elements.
     */
    private int size;
    /**
     * Load coefficient.
     */
    private float k;

    /**
     * Default constructor.
     */
    public SimpleHashSet() {
        this.values = new Object[4];
        this.buckets = 4;
        this.size = 0;
        this.k = 0.75f;

    }

    /**
     * Twice extend number of buckets.
     */
    private void extend() {
        Object[] temp = new Object[values.length * 2];
        buckets = buckets * 2;
        for (Object e : values) {
            if (e != null) {
                temp[e.hashCode() % buckets] = e;
            }
        }
        values = temp;
    }

    /**
     * Add new unique element.
     *
     * @param e Element to add.
     * @return True if the element has been successfully added.
     */
    public boolean add(E e) {
        if (size >= buckets * k) {
            extend();
        }
        if (!this.contains(e)) {
            int bucket = e.hashCode() % buckets;
            while (this.values[bucket] != null) {
                this.extend();
                bucket = e.hashCode() % buckets;
            }
            this.values[bucket] = e;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Check if the set contains the element.
     *
     * @param e element to check.
     * @return True in case of containing this element.
     */
    public boolean contains(E e) {
        return Objects.equals(e, values[e.hashCode() % buckets]);
    }

    /**
     * Remove the element from set.
     *
     * @param e
     * @return
     */
    public boolean remove(E e) {
        if (this.contains(e)) {
            values[e.hashCode() % buckets] = null;
            size--;
            return true;
        }
        return false;
    }
}
