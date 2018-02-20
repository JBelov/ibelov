package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 18.02.2018
 */

public class Matrix {
    public static int[][] multiple(int size) {
        size++;
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = i * j;
                if (j == 0) {
                    array[i][j] = i;
                }
                if (i == 0) {
                    array[i][j] = j;
                }
            }

        }
        return array;
    }

}