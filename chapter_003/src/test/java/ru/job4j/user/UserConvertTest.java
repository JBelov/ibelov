package ru.job4j.user;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {


    @Test
    public void whenItWorks() {
        LinkedList<User> list = new LinkedList<>();
        User user1 = new User(7, "Ivan", "Moscow");
        User user2 = new User(8, "John", "New York");
        list.add(user1);
        list.add(user2);
        UserConvert user = new UserConvert();
        HashMap<Integer, User> result;
        result = user.process(list);
        HashMap<Integer, User> value = new HashMap<>();
        value.put(7, user1);
        value.put(8, user2);
        assertThat(result, is(value));
    }
}