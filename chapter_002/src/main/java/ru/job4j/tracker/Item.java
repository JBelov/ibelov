package ru.job4j.tracker;

import java.util.Objects;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 26.04.2018
 */

public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;

    public Item() {
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.created = System.currentTimeMillis();
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public long getCreated() {
        return this.created;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return Objects.equals(name, other.name)
                && id == other.id
                && desc == other.desc
                && created == other.created;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, created, desc);
    }
}
