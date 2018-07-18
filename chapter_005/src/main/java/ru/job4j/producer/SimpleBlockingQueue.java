package ru.job4j.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private static int size = 3;
    private boolean full = false;
    private boolean empty = true;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        while (full) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        System.out.println("added " + value);
        full = queue.size() == size;
        empty = queue.isEmpty();
        notify();
    }

    public synchronized T poll() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = queue.poll();
        full = queue.size() >= size;
        empty = queue.isEmpty();
        notify();
        System.out.println("taken " + result);
        return result;
    }
}
