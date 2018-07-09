package ru.job4j.jmm;

public class Counter {

    /**
     * Counter.
     */
    private static int counter = 0;

    /**
     * Limit of increments.
     */
    private static int limit;

    Counter(int limit) {
        this.limit = limit;
    }

    /**
     * Increase Counter.
     */
    public static void increase() {
        for (int i = 0; i < limit; i++) {
            counter++;
        }
    }

    /**
     * @return current Counter position.
     */
    public int getCounter() {
        return counter;
    }
}