package ru.job4j.array;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 18.02.2018
 */


public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int index = 0; index < value.length; index++) {
            if (value[index] != data[index]) {
                result = false;
            }
        }
        return result;
    }
    /**
     * Проверяет. что слово содержит другое слово.
     * @param origin проверяемое слово.
     * @param sub проверяемый фрагмент слова.
     * @return если origin содержит sub.
     */
    public static boolean contains(String origin, String sub) {

        boolean result = false;
        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        for (int out = 0; (out < originArray.length - subArray.length) && !result; out++) {
            boolean preResult = true;
            for (int in = 0; in < subArray.length; in++) {
                if (subArray[in] != originArray[in + out]) {
                    preResult = false;
                }
            }
            if (preResult) {
                result = true;
            }
        }
        return result;
    }
}