package ru.job4j.max;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 28.01.2018
 */
public class Max {
    /**
     * Возвращает большее из двух чисел.
     * @param first первое число.
     * @param second второе число.
     * @return большее число.
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }
    /**
     * Возвращает большее из трех чисел.
     * @param first первое число.
     * @param second второе число.
     * @param third третье число.
     * @return большее число.
     */
    public int max(int first, int second, int third) {
        return max(third, max(first, second));
    }
}

