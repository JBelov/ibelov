package ru.job4j.exchange;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Order {
    private String id;
    private String ticket;
    private String action;
    private Float price;
    private int volume;

    public Order(String id, String ticket, String action, Float price, int volume) {
        this.action = action;
        this.ticket = ticket;
        this.id = id;
        this.price = price;
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getTicket() {
        return ticket;
    }

    public Float getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
