package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */

public class Square {
    /**
     * Построение массива с квадратами чисел от 1 до bound.
     * @param bound величина массива
     * @return массив квадратов чисел.
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int index = 0; index != bound; index++) {
            result[index] = (index + 1) * (index + 1);
        }
        return result;
    }
}
