package ru.job4j.tree;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class BinarySearchTreeTest {

    @Test
    public void add() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(6);
        bst.add(1);
        bst.add(4);
        bst.add(8);
        bst.add(3);
        bst.add(6);
        bst.add(2);
        bst.add(5);
        bst.add(7);
        Iterator<Integer> itr = bst.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(1));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(2));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(3));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(4));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(5));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(6));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(6));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(7));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(8));
        assertThat(itr.hasNext(), is(false));
    }
}