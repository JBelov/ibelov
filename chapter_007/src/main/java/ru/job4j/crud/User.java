package ru.job4j.crud;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class User {
    private static volatile AtomicInteger nextId = new AtomicInteger(1);
    private int id;
    private String name;
    private String login;
    private String email;
    private Timestamp createDate;

    /**
     * User class model. Creation date saves while creation.
     *
     * @param name  name
     * @param login login
     * @param email email
     */
    User(String name, String login, String email) {
        this.id = nextId.getAndIncrement();
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, email, createDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(login, user.login);
    }

    @Override
    public String toString() {
        return String.format("%d \n %s \n %s \n %s \n %s \n", id, name, login, email, createDate);
    }
}
