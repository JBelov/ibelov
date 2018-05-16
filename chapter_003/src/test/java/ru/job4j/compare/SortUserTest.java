package ru.job4j.compare;

import org.junit.Test;

import java.util.LinkedList;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenSortedTree() {
        LinkedList<User> users = new LinkedList<>();
        User user1 = new User("Zelan", 30);
        User user2 = new User("Belan", 40);
        User user3 = new User("Celan", 20);
        User user4 = new User("Alan", 10);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        SortUser sortUser = new SortUser();
        TreeSet<User> result = sortUser.sort(users);
        assertThat(result.toArray(), is(new User[]{user4, user3, user1, user2}));
    }
}