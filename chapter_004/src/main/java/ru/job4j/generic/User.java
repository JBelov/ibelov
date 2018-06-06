package ru.job4j.generic;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class User extends Base {

    private String name;

    public User(String id) {
        super(id);
    }

    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}
