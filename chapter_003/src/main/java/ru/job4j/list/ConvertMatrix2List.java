package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (Integer element : row) {
                list.add(element);
            }
        }
        return list;
    }
}
