package sqlru;

import java.sql.Timestamp;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Item {
    int id;
    String url;
    String description;
    Timestamp created;

    /**
     * @param id      default id from the source website.
     * @param url     url.
     * @param subject topic.
     * @param created topic created time.
     */
    public Item(int id, String url, String subject, Timestamp created) {
        this.id = id;
        this.url = url;
        this.description = subject;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getSubject() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }
}

