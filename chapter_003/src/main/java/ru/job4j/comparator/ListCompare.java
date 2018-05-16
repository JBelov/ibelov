package ru.job4j.comparator;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {

        int minSize = left.length() < right.length() ? left.length() : right.length();
        int result = 0;
        for (int index = 0; index < minSize; index++) {
            result = Character.compare(left.charAt(index), right.charAt(index));
            if (result != 0) {
                break;
            }
        }

        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}