package ru.job4j.compare;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
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

    @Test
    public void whenSortedByName() {
        LinkedList<User> users = new LinkedList<>();
        User user1 = new User("Smith", 30);
        User user2 = new User("Sam", 40);
        User user3 = new User("John", 20);
        User user4 = new User("Jo", 10);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(users);
        assertThat(result.toArray(), is(new User[]{user4, user2, user3, user1}));
    }

    @Test
    public void whenSortedByTwoParam() {
        LinkedList<User> users = new LinkedList<>();
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Сергей", 20);
        User user4 = new User("Иван", 25);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(users);
        assertThat(result.toArray(), is(new User[]{user4, user2, user3, user1}));
    }
}