package jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
@XmlRootElement(name = "entry")
public class Entry {
    /**
     * Field.
     */
    private int field;

    /**
     * Empty Constructor.
     */
    public Entry() {
    }

    /**
     * Initial constructor.
     *
     * @param field - поле.
     */
    public Entry(int field) {
        this.field = field;
    }

    /**
     * Getter.
     *
     * @return
     */
    @XmlElement
    public int getField() {
        return field;
    }
}