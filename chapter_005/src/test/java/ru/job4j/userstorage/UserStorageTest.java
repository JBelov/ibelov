package ru.job4j.userstorage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddAndTransferOk() {
        UserStorage storege = new UserStorage();
        storege.add(new User(1, 100));
        storege.add(new User(2, 200));
        storege.transfer(1, 2, 50);
        assertThat(storege.get(1).getAmount(), is(50));
        assertThat(storege.get(2).getAmount(), is(250));
    }
}