package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.Figure;
import ru.job4j.chess.ImpossibleMoveException;

public class Bishop extends Figure {

    private Cell position;

    public Bishop(Cell dest) {
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
    public Bishop copy(Cell dest) {
        Bishop newBishop = new Bishop(dest);
        return newBishop;
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
        if (Math.abs(shiftX) != Math.abs(shiftY)) {
            throw new ImpossibleMoveException();
        }
        int stepX = (dest.x - source.x) > 0 ? 1 : -1;
        int stepY = (dest.y - source.y) > 0 ? 1 : -1;
        int currentX = source.x;
        int currentY = source.y;
        Cell[] way = new Cell[Math.abs(shiftX)];
        int index = 0;
        while (index < Math.abs(shiftX)) {
            currentX += stepX;
            currentY += stepY;
            Cell step = new Cell(currentX, currentY);
            way[index] = step;
            index++;
        }
        return way;
    }
}
