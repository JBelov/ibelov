package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 18.02.2018
 */

public class FindLoop {
    /**
     * Поиск порядкового номера элемента в массиве.
     * @param data массив для поиска.
     * @param element элемент, который ищем.
     * @return порядковый номер, если элемента нет в массиве, то возвращаем -1.
     */
    public static int indexOf(int[] data, int element) {
    int result = -1;
    for (int index = 0; index != data.length; index++) {
        if (data[index] == element) {
            result = index;

            break;
        }
    }
    return result;
}
}