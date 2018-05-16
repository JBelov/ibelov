package ru.job4j.compare;

import java.util.Comparator;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getName() {
        return name;
    }

    public int compareTo(User user) {
        return this.getAge().compareTo(user.getAge());
    }
}
