package ru.job4j.map;

import org.junit.Test;

import java.util.*;

public class UserTest {

    @Test
    public void mapTest() {
        Map<User, Object> users = new HashMap<>();
        Calendar date = new GregorianCalendar(1985, 01, 01);
        User user1 = new User("Ivan", 1, date);
        User user2 = new User("Ivan", 1, date);
        users.put(user1, "first");
        users.put(user2, "second");
        System.out.println("user1 hash " + user1.hashCode());
        System.out.println("user2 hash " + user2.hashCode());
        System.out.println(users);
    }

}