package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */

public class Turn {
    /**
     * Инверсия массива.
     * @param array исходный массив.
     * @return инвертированный массив.
     */
    public static int[] back(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int temp1 = array[index];
            int temp2 = array[array.length - 1 - index];
            array[index] = temp2;
            array[array.length - 1 - index] = temp1;
            }
        return array;
        }
    }
