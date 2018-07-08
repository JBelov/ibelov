package ru.job4j.exchange;

import java.util.HashMap;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Exchange {
    private HashMap<String, Dom> domes = new HashMap<>();

    /**
     * Add the new order to the Exchange.
     *
     * @param order order to be added.
     * @return true in case of successful added order.
     */
    public boolean add(Order order) {
        if (!domes.containsKey(order.getTicket())) {
            domes.put(order.getTicket(), new Dom());
        }
        return domes.get(order.getTicket()).add(order);
    }

    /**
     * Remove order from the Exchange by its ID.
     *
     * @param id id of order to be removed.
     * @return true in case of successful deleting of order.
     */
    public boolean remove(String id) {
        boolean result = false;
        for (Dom dom : domes.values()) {
            result |= dom.remove(id);
        }
        return result;
    }

    /**
     * Get exact DOM by its ticket.
     *
     * @param ticket exchange ticket.
     * @return DOM or null.
     */
    public Dom getBook(String ticket) {
        return domes.get(ticket);
    }
}
