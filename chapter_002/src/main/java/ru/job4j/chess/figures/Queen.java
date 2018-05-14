package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.Figure;
import ru.job4j.chess.ImpossibleMoveException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Queen extends Figure {

    private Cell position;

    public Queen(Cell dest) {
        this.position = dest;
    }

    public Cell getPosition() {
        return this.position;
    }

    /**
     * Возвращает копию фигуры по заданным координатам.
     *
     * @param dest клетка назначения фигуры.
     * @return новая фигура с новыми координатами.
     */
    public Queen copy(Cell dest) {
        Queen newFigure = new Queen(dest);
        return newFigure;
    }

    /**
     * Проверяем возможность хода в данную точку, и возвращаем маршрут.
     *
     * @param source исходная точка фигуры.
     * @param dest   точка назначения.
     * @return массив клеток по которым должна пройти фигура.
     */
    public Cell[] way(Cell source, Cell dest) {
        int shiftX = dest.x - source.x;
        int shiftY = dest.y - source.y;
        int shift = Math.abs(shiftX) + Math.abs(shiftY);
        if (!(((dest.x == source.x) || (dest.y == source.y)) || (Math.abs(shiftX) == Math.abs(shiftY)))) {
            throw new ImpossibleMoveException();
        }
        int stepX = (dest.x - source.x) > 0 ? 1 : -1;
        int stepY = (dest.y - source.y) > 0 ? 1 : -1;
        stepX = (dest.x - source.x) == 0 ? 0 : stepX;
        stepY = (dest.y - source.y) == 0 ? 0 : stepY;
        int currentX = source.x;
        int currentY = source.y;
        Cell[] way = new Cell[shift];
        int index = 0;
        while (index < shift) {
            currentX += stepX;
            currentY += stepY;
            Cell step = new Cell(currentX, currentY);
            way[index] = step;
            index++;
        }
        return way;
    }
}
