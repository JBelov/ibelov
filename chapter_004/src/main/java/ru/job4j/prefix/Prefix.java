package ru.job4j.prefix;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Prefix {
    public String find(String[] input) {
        String result = "";
        for (int outer = 0; outer < input.length - 1; outer++) {
            for (int inner = outer + 1; inner < input.length; inner++) {
                if (input[outer].startsWith(input[inner].substring(0, 1))) {
                    String candidate = input[outer].length() > input[inner].length() ? input[outer] : input[inner];
                    result = candidate.length() > result.length() ? candidate : result;
                }
            }
        }
        return result;
    }
}
