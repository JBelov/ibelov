package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 * <p>
 * Abstract store that implements Store interface and use Simple Array with classes extending Base to store elements.
 */

public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Create SimpleArray to store elements.
     */
    private final SimpleArray<T> store;
    private int size;

    /**
     * Constructor for with current size.
     *
     * @param size maximum elements number.
     */
    public AbstractStore(int size) {
        store = new SimpleArray<>(size);
        this.size = size;
    }

    /**
     * Finds int index of element for the current String id.
     *
     * @param id String id.
     * @return Element with current id, or int -1 if no such elements.
     */
    private int findIndexById(String id) {
        for (int index = 0; index < store.getSize(); index++) {
            if (store.get(index).getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Adds new element in store.
     *
     * @param model new element (extends Base).
     */
    @Override
    public void add(T model) {
        store.add(model);
    }

    /**
     * Replaces the element by it's id.
     *
     * @param id    of element to be replaced.
     * @param model new element to replace for.
     * @return true in case of successful replacement.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = findIndexById(id);
        if (index >= 0) {
            store.set(index, model);
            result = true;
        }
        return result;
    }

    /**
     * Deletes the element by it's id.
     *
     * @param id of element to be deleted.
     * @return true in case of successful deletion.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndexById(id);
        if (index >= 0) {
            store.delete(index);
            result = true;
        }
        return result;
    }

    /**
     * Returns element by it's id.
     *
     * @param id id element to return.
     * @return element by id, or null in case of no element with this id.
     */
    @Override
    public T findById(String id) {
        Iterator<T> itr = store.iterator();
        while (itr.hasNext()) {
            T element = itr.next();
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }
}


