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
        this.size = 0;
        this.k = 0.75f;
    }

    /**
     * Twice extend number of buckets.
     */
    private void extend() {
        Object[] temp = new Object[values.length * 2];
        for (Object e : values) {
            if (e != null) {
                temp[e.hashCode() & (temp.length - 1)] = e;
            }
        }
        values = temp;
    }

    /**
     * Calculate the bucket number for some item by its hashCode.
     *
     * @return bucket number.
     */
    private int getBucket(Object e) {
        return e.hashCode() & (values.length - 1);
    }

    /**
     * Add new unique element.
     *
     * @param e Element to add.
     * @return True if the element has been successfully added.
     */
    public boolean add(E e) {
        if (size >= values.length * k) {
            extend();
        }
        if (!this.contains(e)) {
            int bucket = getBucket(e);
            while (this.values[bucket] != null) {
                this.extend();
                bucket = getBucket(e);
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
        return Objects.equals(e, values[getBucket(e)]);
    }

    /**
     * Remove the element from set.
     */
    public boolean remove(E e) {
        if (this.contains(e)) {
            values[getBucket(e)] = null;
            size--;
            return true;
        }
        return false;
    }
}
