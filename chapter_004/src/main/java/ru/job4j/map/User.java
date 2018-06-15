package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class User {
    public String name;
    public int children;
    public Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        User other = (User) o;
        return Objects.equals(name, other.name)
                && children == other.children
                && Objects.equals(birthday, other.birthday);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() + children * 31 + birthday.hashCode() : 0;
    }

}
