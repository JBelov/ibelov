package ru.job4j.lock;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class SimpleLock {

    private Thread owner;
    private boolean locked = false;

    public synchronized void lock() {
        while (locked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.owner = Thread.currentThread();
        this.locked = true;
    }

    public synchronized void unlock() {
        if (locked && this.owner == Thread.currentThread()) {
            this.locked = false;
            this.owner = null;
            notifyAll();
        }
    }
}