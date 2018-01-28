package ru.job4j.loop;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 28.01.2018
 */

public class Factorial  {
    public int calc(int n) {
        int result  = 1;
        for (int index = 1; index <= n; index++) {
            result = result * index;
        }
        return result;
    }
}
