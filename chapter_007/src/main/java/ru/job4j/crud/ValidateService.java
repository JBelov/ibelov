package ru.job4j.crud;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {

    private static ValidateService ourInstance = new ValidateService();

    public static ValidateService getInstance() {
        return ourInstance;
    }

    private final Store<User> logic = MemoryStore.getInstance();

    synchronized Boolean add(User user) {
        logic.add(user);
        return true;
    }

    synchronized Boolean update(String name, String login, String email, int id) {
        boolean success = false;
        if (logic.findById(id).isPresent()) {
            logic.update(name, login, email, id);
            success = true;
        }
        return success;
    }

    synchronized Boolean delete(int id) {
        boolean success = false;
        if (logic.findById(id).isPresent()) {
            logic.delete(id);
            success = true;
        }
        return success;
    }

    List<User> findAll() {
        return logic.findAll();
    }

    Optional<User> findById(int id) {
        return logic.findById(id);
    }
}
