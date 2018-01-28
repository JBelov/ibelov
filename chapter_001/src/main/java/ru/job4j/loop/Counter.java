package ru.job4j.loop;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 28.01.2018
 */

public class Counter {
    public int add(int start, int finish) {
        int summ = 0;
        for (int index = start; index <= finish; index++) {
            if (index % 2 == 0) {
                summ = summ + index;
            }

        }
        return summ;
    }
}
