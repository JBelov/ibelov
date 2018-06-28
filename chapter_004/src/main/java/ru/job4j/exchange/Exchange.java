package ru.job4j.exchange;

import java.util.LinkedList;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Exchange {
    private LinkedList<Dom> domes = new LinkedList<>();

    /**
     * Add the new order to the Exchange.
     *
     * @param order order to be added.
     * @return true in case of successful added order.
     */
    public boolean add(Order order) {
        String book = order.getTicket();
        for (Dom dom : domes) {
            if (dom.getTicket().equals(book)) {
                return dom.add(order);
            }
        }
        domes.add(new Dom(order.getTicket()));
        this.add(order);
        return false;
    }

    /**
     * Remove order from the Exchange by its ID.
     *
     * @param id id of order to be removed.
     * @return true in case of successful deleting of order.
     */
    public boolean remove(String id) {
        for (Dom dom : domes) {
            if (dom.remove(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get exact DOM by its ticket.
     *
     * @param ticket exchange ticket.
     * @return DOM or null.
     */
    public Dom getBook(String ticket) {
        for (Dom dom : domes) {
            if (dom.getTicket().equals(ticket)) {
                return dom;
            }
        }
        return null;
    }
}
