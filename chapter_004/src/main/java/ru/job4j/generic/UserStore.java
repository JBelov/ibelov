package ru.job4j.generic;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class UserStore extends AbstractStore<User> {

    public UserStore(int currentSize) {
        super(currentSize);
    }
}
