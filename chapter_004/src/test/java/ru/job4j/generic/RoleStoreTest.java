package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class RoleStoreTest {

    @Test(expected = IllegalStateException.class)
    public void whenAddReplaceDeleteFindRolesWorksFine() {
        RoleStore roles = new RoleStore(4);
        Role role1 = new Role("5794379");
        Role role2 = new Role("8574343", 777);
        Role role3 = new Role("3794374");
        Role role4 = new Role("4789217");
        Role role5 = new Role("8574343", 555);
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roles.add(role4);
        roles.replace("8574343", role5);

        assertThat(roles.delete("4789217"), is(true));
        assertThat(roles.delete("4789217"), is(false));
        assertThat(roles.findById("3794374"), is(role3));
        assertThat(roles.findById("8574343").getPermissions(), is(555));

        roles.add(new Role("5473784098"));
        roles.add(new Role("5473784098"));
    }

}

