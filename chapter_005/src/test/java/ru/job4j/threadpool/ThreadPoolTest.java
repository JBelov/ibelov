package ru.job4j.threadpool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class ThreadPoolTest {

    private static AtomicInteger counter = new AtomicInteger(0);

    class NewThread implements Runnable {
        int id;

        private NewThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Started Task #" + id + " " + Thread.currentThread());
            counter.incrementAndGet();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ended Task #" + id);
        }
    }

    @Test
    public void workTestMultiThread() {
        ThreadPool pool = new ThreadPool();

        for (int i = 0; i < 100; i++) {
            pool.work(new NewThread(i));
        }

        try {
            Thread.sleep(5000);
            pool.shutdown();
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(counter.get() == 5 * Runtime.getRuntime().availableProcessors());
    }
}