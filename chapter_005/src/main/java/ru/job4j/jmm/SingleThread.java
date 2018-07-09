package ru.job4j.jmm;

public class SingleThread extends Thread implements Runnable {
    @Override
    public void run() {
        Counter.increase();
    }
}
