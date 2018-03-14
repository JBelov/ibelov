package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */

public class BubbleSort {
    /**
     * Сортировка массива "пузырьком".
     * @param array исходный массив.
     * @return отсортированный массив.
     */
    public static int[] sort(int[] array) {
        for (int outside = 1; outside < array.length; outside++) {
            for (int inside = 0; inside < array.length - outside; inside++) {
                if (array[inside] > array[inside + 1]) {
                    int temp = array[inside];
                    array[inside] = array [inside + 1];
                    array [inside + 1] = temp;
                }
            }

        }
        return array;
    }

}