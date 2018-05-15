package ru.job4j.user;

import java.util.HashMap;
import java.util.List;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class UserConvert {
    /**
     * Метод возвращает HashMap объектов типа User, где ключ равен userId из заданного листа объектов.
     * @param list входящий лист объектов.
     * @return HashMap объектов типа User.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
