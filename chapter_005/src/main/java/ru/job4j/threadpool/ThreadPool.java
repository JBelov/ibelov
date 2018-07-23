package ru.job4j.threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class ThreadPool {
    private final List<Worker> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();

    ThreadPool() {
        for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
            Worker worker = new Worker();
            threads.add(worker);
            worker.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Worker worker : threads) {
            worker.shutdown();
        }
    }

    private final class Worker extends Thread {
        private Boolean shutdown = false;

        private void shutdown() {
            shutdown = true;
        }

        @Override
        public void run() {
            System.out.println("Worker Started");
            while (!isInterrupted() && !shutdown) {
                if (!tasks.isEmpty()) {
                    try {
                        tasks.poll().run();
                    } catch (NullPointerException n) {
                    }
                }
            }
            System.out.println("Worker Finished");
        }
    }
}

