package ru.job4j.list;

import static java.lang.Math.ceil;

import java.util.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) ceil((double) list.size() / (double) rows);
        int[][] array = new int[rows][cells];
        ListIterator<Integer> itr = list.listIterator();
        for (int row = 0; row < rows; row++) {
            for (int cell = 0; cell < cells; cell++) {
                if (itr.hasNext()) {
                    array[row][cell] = itr.next();
                } else {
                    array[row][cell] = 0;
                }
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int element : array) {
                result.add(element);
            }
        }
        return result;
    }
}

