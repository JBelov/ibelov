package ru.job4j.crud.logic;

import ru.job4j.crud.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 * <p>
 * Persistent Layout. Simple users store.
 */
public class MemoryStore implements Store<User> {

    private static MemoryStore ourInstance = new MemoryStore();

    public static MemoryStore getInstance() {
        return ourInstance;
    }

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    @Override
    public void add(User user) {
        this.users.putIfAbsent(user.getId(), user);
    }

    @Override
    synchronized public void update(String name, String login, String email, int id) {
        if (name != null) {
            users.get(id).setName(name);
        }
        if (login != null) {
            users.get(id).setLogin(login);
        }
        if (email != null) {
            users.get(id).setEmail(email);
        }
    }

    @Override
    public void delete(int id) {
        this.users.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(this.users.get(id));
    }
}
