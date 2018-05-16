package ru.job4j.compare;

import java.util.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SortUser {

    /**
     * Метод сортирует входящий лист элементов типа User в порядке возрастания возраста.
     *
     * @param users входящий лист.
     * @return Возвращаем отсортированный TreeSet.
     */
    TreeSet<User> sort(List<User> users) {
        TreeSet<User> sortUsers = new TreeSet<>();
        sortUsers.addAll(users);
        return sortUsers;
    }

    /**
     * Метод сортирует входящий List объектов User по длине имени.
     *
     * @param users входящий List.
     * @return отсортированный List.
     */
    public List<User> sortNameLength(List<User> users) {

        users.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        return Integer.compare(u1.getName().length(), u2.getName().length());
                    }
                }
        );

        return users;
    }

    /**
     * Метод сортирует входящий List объектов User по имени, затем по возрасту.
     *
     * @param users входящий List.
     * @return отсортированный List.
     */
    public List<User> sortByAllFields(List<User> users) {

        users.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        int result = u1.getName().compareTo(u2.getName());
                        return result != 0 ? result : Integer.compare(u1.getAge(), u2.getAge());
                    }
                }
        );
        return users;
    }
}
