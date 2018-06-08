package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class CycleTest {

    @Test
    public void whenNoCycleThenResultIsFalse() {

        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        Cycle cycle = new Cycle();
        boolean result = cycle.hasCycle(first);

        assertThat(result, is(false));

    }

    @Test
    public void whenLastToFirstCycleThenResultIsTrue() {

        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;

        Cycle cycle = new Cycle();
        boolean result = cycle.hasCycle(third);

        assertThat(result, is(true));

    }

    @Test
    public void whenFifthToThirdCycleThenResultIsTrue() {

        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = third;

        Cycle cycle = new Cycle();
        boolean result = cycle.hasCycle(first);

        assertThat(result, is(true));

    }
}