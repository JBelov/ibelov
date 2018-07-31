package ru.job4j.email;

import java.util.concurrent.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {

        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String subject = "Notification " + user.getUsername() + " to email " + user.getEmail();
                String body = "Add a new event to " + user.getUsername();
                send(subject, body, user.getEmail());
            }
        });
    }

    private void send(String subject, String body, String email) {
        System.out.println("subject: " + subject);
        System.out.println("body: " + body);
        System.out.println("email: " + email);
        System.out.println(System.lineSeparator());
    }

    public void shutdown() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
