package ru.job4j.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final static int SIZE = 3;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        while (queue.size() == SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        System.out.println("added " + value);
        notify();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = queue.poll();
        notify();
        System.out.println("taken " + result);
        return result;
    }
}
