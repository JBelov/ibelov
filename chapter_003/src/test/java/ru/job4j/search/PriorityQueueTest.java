package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("middle", 2));
        queue.put(new Task("middle", 4));
        queue.put(new Task("low", 7));
        queue.put(new Task("low", 10));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 5));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}