package ru.job4j.nonblock;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Base {
    private int id;
    private int version;
    private String value;

    public Base(int id, String value) {
        this.id = id;
        this.version = 0;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void updateVersion() {
        this.version++;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                ", value='" + value + '\'' +
                '}';
    }
}
