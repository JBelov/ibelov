package ru.job4j.email;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class EmailNotificationTest {

    @Test
    public void emailToTest() {
        EmailNotification email = new EmailNotification();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.println("Added User #" + i + " " + Thread.currentThread().getName());
                email.emailTo(new User("user " + i, "user" + i + "@google.com"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        email.shutdown();
    }
}