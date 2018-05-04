package ru.job4j.tracker;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 04.05.2018
 */

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();

}
