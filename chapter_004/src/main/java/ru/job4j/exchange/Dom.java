package ru.job4j.exchange;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Dom {


    /**
     * List of Bids.
     */
    private LinkedList<Order> bids = new LinkedList<>();
    /**
     * List of Asks.
     */
    private LinkedList<Order> asks = new LinkedList<>();


    /**
     * Checks if the dom has order with this id.
     *
     * @param id
     * @return true if it has.
     */
    private boolean hasId(String id) {
        for (Order order : bids) {
            if (order.getId().equals(id)) {
                return true;
            }
        }
        for (Order order : asks) {
            if (order.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add the new order to the DOM.
     *
     * @param order
     * @return
     */
    public boolean add(Order order) {
        if (hasId(order.getId())) {
            return false;
        }
        if (order.getAction().equals("BUY")) {
            bids.add(order);
            bids.sort((o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()));
        }
        if (order.getAction().equals("SELL")) {
            asks.add(order);
            asks.sort((o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()));
        }
        while (trade()) {
            trade();
        }
        return true;
    }

    /**
     * Remove the order from the DOM by its ID.
     *
     * @param id
     * @return
     */
    public boolean remove(String id) {
        return (bids.removeIf((b) -> b.getId().equals(id))) || (asks.removeIf((a) -> a.getId().equals(id)));
    }

    private boolean trade() {
        boolean result = false;
        if (!bids.isEmpty() && !asks.isEmpty()) {
            Order bid = bids.getLast();
            Order ask = asks.getFirst();
            if (bid.getPrice() >= ask.getPrice()) {
                if (ask.getVolume() > bid.getVolume()) {
                    ask.setVolume(ask.getVolume() - bid.getVolume());
                    remove(bid.getId());
                    result = true;
                } else {
                    bid.setVolume(bid.getVolume() - ask.getVolume());
                    remove(ask.getId());
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * It makes a TreeMap of DOM grouped by price and reverse sorted.
     *
     * @param orders incoming list of orders.
     * @return sorted DOM levels as a TreeMap.
     */
    private TreeMap<Float, Integer> combo(LinkedList<Order> orders) {
        Map<Float, Integer> dom = orders
                .stream()
                .collect(Collectors.toMap(
                        b -> b.getPrice(),
                        b -> b.getVolume(),
                        (volume1, volume2) -> volume1 + volume2
                ));
        TreeMap<Float, Integer> reverseDom = new TreeMap<>(Collections.reverseOrder());
        reverseDom.putAll(dom);
        return reverseDom;
    }

    @Override
    public String toString() {

        TreeMap<Float, Integer> bidsDome = new TreeMap<>(combo(bids));
        TreeMap<Float, Integer> asksDome = new TreeMap<>(combo(asks));
        StringBuilder result = new StringBuilder();
        asksDome.forEach((price, volume) ->
                result.append(volume + "  " + price + System.lineSeparator()));
        bidsDome.forEach((price, volume) ->
                result.append("     " + price + "  " + volume + System.lineSeparator()));
        return result.toString();
    }
}

