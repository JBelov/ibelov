package ru.job4j.crud.logic;

import ru.job4j.crud.models.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T extends User> {

    void add(T user);

    void update(T user);

    void delete(String id);

    List<T> findAll();

    Optional<T> findById(String id);

}
