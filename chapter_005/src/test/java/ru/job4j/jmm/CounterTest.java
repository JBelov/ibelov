package ru.job4j.jmm;

import org.junit.Test;

import static org.junit.Assert.*;

public class CounterTest {

    @Test
    public void increase() throws InterruptedException {
        int singleLimit = 1000000000;
        Counter counter = new Counter(singleLimit);
        Thread first = new SingleThread();
        Thread second = new SingleThread();
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(counter.getCounter());
        assertTrue(!first.isAlive() && !second.isAlive() && singleLimit * 2 > counter.getCounter());
    }
}