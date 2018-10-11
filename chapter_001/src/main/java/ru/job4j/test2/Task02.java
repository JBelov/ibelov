package ru.job4j.test2;

/**
 * @author Ivan Belov (ivan@belov.org)
 */
public class Task02 {
    public static long calculate(long m, long r) {
        long result = 1;
        for (long i = 1; i <= m; i++) {
            if (i <= m - r) {
                result *= i;
                result *= i;
            } else if (i <= r) {
                result *= i;
            }
            result *= i;
        }
        return result;
    }
}
