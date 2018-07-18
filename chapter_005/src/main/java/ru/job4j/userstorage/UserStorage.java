package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.TreeMap;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    final private TreeMap<Integer, User> users;

    public UserStorage() {
        users = new TreeMap<>();
    }

    public synchronized void add(User user) {
        users.put(user.getId(), user);
    }

    public synchronized User get(int id) {
        return users.get(id);
    }

    public synchronized void transfer(int id1, int id2, int amount) {
        if (users.containsKey(id1)
                && users.containsKey(id2)
                && users.get(id1).getAmount() >= amount) {
            users.get(id1).decreaseAmount(amount);
            users.get(id2).increaseAmount(amount);
        }
    }
}
