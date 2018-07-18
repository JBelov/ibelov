package ru.job4j.producer;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAndConsumerWorks() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        int[] source = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = new int[9];
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    queue.offer(source[i]);
                }

            }
        };
        Thread consumer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    result[i] = queue.poll();
                }
            }
        };
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Arrays.equals(source, result));
    }
}