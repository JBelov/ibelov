package ru.job4j.loop;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 01.02.2018
 */

public class Board {
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if ((i + j) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }

        return screen.toString();
    }
}
