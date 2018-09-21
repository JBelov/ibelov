package ru.job4j.crud.logic;

import ru.job4j.crud.models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {

    private static final Random RN = new Random(100);

    private static ValidateService ourInstance = new ValidateService();

    public static ValidateService getInstance() {
        return ourInstance;
    }

    private final Store<User> logic = DbStore.getInstance();

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }


    synchronized public Boolean add(User user) {
        user.setId(generateId());
        logic.add(user);
        return true;
    }

    synchronized public Boolean update(User user) {
        boolean success = false;
        if (logic.findById(user.getId()).isPresent()) {
            logic.update(user);
            success = true;
        }
        return success;
    }

    synchronized public Boolean delete(String id) {
        boolean success = false;
        if (logic.findById(id).isPresent()) {
            logic.delete(id);
            success = true;
        }
        return success;
    }

    public List<User> findAll() {
        return logic.findAll();
    }

    public Optional<User> findById(String id) {
        return logic.findById(id);
    }
}
