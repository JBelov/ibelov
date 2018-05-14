package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.Figure;
import ru.job4j.chess.ImpossibleMoveException;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Pawn extends Figure {

    private Cell position;

    public Pawn(Cell dest) {
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
    public Pawn copy(Cell dest) {
        Pawn newFigure = new Pawn(dest);
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
        if (!((dest.x == source.x) && (dest.y - source.y == 1))) {
            throw new ImpossibleMoveException();
        }
        Cell[] way = new Cell[1];
        way[0] = dest;
        return way;
    }
}
