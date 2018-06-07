package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleQueueTest {

    @Test(expected = NoSuchElementException.class)
    public void push() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.add("one");
        queue.add("two");
        queue.add("three");
        assertThat(queue.poll(), is("one"));
        assertThat(queue.poll(), is("two"));
        assertThat(queue.poll(), is("three"));
        queue.poll();
    }

}