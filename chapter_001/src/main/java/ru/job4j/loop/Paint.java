package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 06.02.2018
 */

public class Paint {
    /**
     * Рисует правосторонний треугольник.
     * @param height высота треуголльника.
     * @return правосторонний треугольник.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Рисует левосторонний треугольник.
     * @param height высота треугольника.
     * @return левосторонний треугольник.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }
    /**
     * Рисует пирамиду.
     * @param height высота пирамиды.
     * @return
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}