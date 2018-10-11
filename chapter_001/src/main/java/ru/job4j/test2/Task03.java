package ru.job4j.test2;

/**
 * @author Ivan Belov (ivan@belov.org)
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Task03 {
    public static void textAnalyze(String text) {
        System.out.println(Arrays.toString(
                (Arrays.stream(text.toLowerCase().split("\\p{P}?[ \\t\\n\\r]+"))
                        .collect(Collectors.groupingBy(f -> f, Collectors.counting()))).entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new)).entrySet().toArray()
                )
        );
    }
}
