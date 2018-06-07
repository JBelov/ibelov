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

public class SimpleStackTest {

    @Test(expected = NoSuchElementException.class)
    public void push() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        stack.poll();
    }

}