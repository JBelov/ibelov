package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class User {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.passport = passport;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(name, other.name)
                && passport == other.passport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }
}
