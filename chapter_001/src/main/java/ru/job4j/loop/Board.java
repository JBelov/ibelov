package ru.job4j.loop;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 01.02.2018
 */

public class Board {
    /**
     * Построение шахматной доски.
     * @param width ширина доски.
     * @param height высота доски.
     * @return строка для вывода шахматной доски в консоль.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String newLine = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(newLine);
        }
        return screen.toString();
    }
}
