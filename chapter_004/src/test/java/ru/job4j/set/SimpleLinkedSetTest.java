package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleLinkedSetTest {

    @Test(expected = NoSuchElementException.class)
    public void whenAddAndGetWorksCorrect() {
        SimpleLinkedSet<Integer> list = new SimpleLinkedSet<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        assertThat(list.get(0), is(4));
        assertThat(list.get(1), is(3));
        assertThat(list.get(2), is(2));
        assertThat(list.get(3), is(1));
        list.get(6);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorWorksCorrect() {
        SimpleLinkedSet<String> list = new SimpleLinkedSet<>();
        list.add("one");
        list.add("two");
        list.add("two");
        list.add("two");
        list.add("three");
        list.add("four");
        Iterator<String> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("four"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(false));
        list.add("five");
        iterator.hasNext();
    }
}
