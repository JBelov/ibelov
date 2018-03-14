package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 20.02.2018
 */

public class ArrayDuplicate {
    /**
     * Удаляет дубликаты из массива.
     * @param array исходный массив.
     * @return массив без дубликатов.
     * out - индекс для внешнего цикла
     * in - индекс для внутреннего цикла.
     */
    public static String[] remove(String[] array) {
        int shift = array.length;
        for (int out = 0; out < shift; out++) {
            for (int in = out + 1; in < shift; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[shift - 1];
                    shift--;
                    in--;

                }
            }
        }
        return Arrays.copyOf(array, shift);
    }

}
