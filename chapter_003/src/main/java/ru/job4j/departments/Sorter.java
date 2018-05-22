package ru.job4j.departments;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Sorter {

    /**
     * This method adds the missing parent departments to array.
     *
     * @param input input array of departments.
     * @return array with added all missing parents.
     */
    private ArrayList<String> addParents(ArrayList<String> input) {
        for (int index = 0; index < input.size(); index++) {
            if (input.get(index).contains("\\")) {
                String path = input.get(index).substring(0, input.get(index).lastIndexOf('\\'));
                if (!input.contains(path)) {
                    input.add(path);
                }
            }
        }
        return input;
    }

    /**
     * This method sorts array of departments by increase.
     *
     * @param input input array of departments.
     * @return sorted array with correct structure.
     */
    public ArrayList<String> sortIncrease(ArrayList<String> input) {
        input = addParents(input);
        input.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return input;
    }

    /**
     * This method sorts array of departments by decrease.
     *
     * @param input input array of departments.
     * @return sorted array with correct structure.
     */
    public ArrayList<String> sortDecrease(ArrayList<String> input) {
        input = addParents(input);
        input.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                if (o1.length() < o2.length() && o2.startsWith(o1)) {
                    result = -1;
                } else {
                    if (o1.length() > o2.length() && o1.startsWith(o2)) {
                        result = 1;
                    } else {
                        result = o2.compareTo(o1);
                    }
                }
                return result;
            }
        });
        return input;
    }
}
