package jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "entries")
public class Entries {

    /**
     * Entries list.
     */
    private List<Entry> entries;

    /**
     * Empty Constructor.
     */
    public Entries() {
    }

    /**
     * Initial constructor.
     *
     * @param entries list of entries.
     */
    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * Return entry.
     *
     * @return
     */
    public List<Entry> getEntry() {
        return entries;
    }

    /**
     * Set entry.
     *
     * @param entry
     */
    public void setEntry(List<Entry> entry) {
        this.entries = entry;
    }
}