package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */

public class Square {
    public int[] calculate(int bound) {

        int[] rsl = new int[bound];

        for (int index = 0; index != bound; index++) {

            rsl[index] = (index + 1) * (index + 1);

        }

        return rsl;

    }


}
