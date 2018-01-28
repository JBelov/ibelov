package ru.job4j.max;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 28.01.2018
 */
public class Max {
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }
    public int max(int first, int second, int third) {
        Max temp = new Max();
        return temp.max(third, temp.max(first, second));
    }

}

