package ru.job4j.generic;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Role extends Base {

    private int permissions;

    public Role(String id) {
        super(id);
    }

    public Role(String id, int permissions) {
        super(id);
        this.permissions = permissions;
    }

    public int getPermissions() {
        return this.permissions;
    }
}
