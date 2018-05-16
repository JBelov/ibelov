package ru.job4j.compare;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SortUser {
    TreeSet<User> sort(List<User> users) {
        TreeSet<User> sortUsers = new TreeSet<>();
        sortUsers.addAll(users);
        return sortUsers;
    }
}
