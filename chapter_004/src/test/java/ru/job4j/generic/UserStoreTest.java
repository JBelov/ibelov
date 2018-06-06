package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class UserStoreTest {

    @Test
    public void whenAddReplaceDeleteFindUsersWorksFine() {
        UserStore users = new UserStore(10);
        User user1 = new User("5794379");
        User user2 = new User("8574343", "Ivan");
        User user3 = new User("3794374");
        User user4 = new User("4789217");
        User user5 = new User("8574343", "Petr");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.replace("8574343", user5);

        assertThat(users.delete("4789217"), is(true));
        assertThat(users.delete("4789217"), is(false));
        assertThat(users.findById("3794374"), is(user3));
        assertThat(users.findById("8574343").getName(), is("Petr"));
    }

}