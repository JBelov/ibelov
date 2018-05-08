package ru.job4j.chess;

/**
 * Абстрактный класс шахматной фигуры.
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public abstract class Figure {

    final private Cell position;

    public Figure() {
        this.position = null;
    }

    public Figure(Cell dest) {
        this.position = dest;
    }

    public Cell getPosition() {
        return position;
    }

    /**
     * Абстрактный метод перемещения фигуры.
     *
     * @param source начальная точка.
     * @param dest   конечная точка.
     * @return
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Абстрактный метод копирования фигуры.
     *
     * @param dest клетка назначения фигуры.
     * @return возвращает фигуру по новым координатам.
     */
    public abstract Figure copy(Cell dest);

}



