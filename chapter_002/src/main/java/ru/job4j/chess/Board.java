package ru.job4j.chess;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Board {

    /**
     * Позиция последней фигуры в массиве фигур.
     */
    private int position = 0;

    /**
     * Инициализация массива фигур на доске.
     */
    private Figure[] figures = new Figure[32];

    /**
     * Добавление фиуры на доску.
     *
     * @param figure добавляемая фигура.
     */
    public void add(Figure figure) {
        if (position < figures.length) {
            figures[position++] = figure;
        }
    }

    /**
     * Метод проверяет наличие фигуры в заданной клетке.
     *
     * @param cell заданная клетка.
     * @return возвращает объект фигуры или null.
     */
    private Figure checkCell(Cell cell) {
        for (int index = 0; index < position; index++) {
            if (figures[index].getPosition().x == cell.x && figures[index].getPosition().y == cell.y) {
                return figures[index];
            }
        }
        return null;
    }

    /**
     * Метод возвращает номер фигуры в массиве по заданной клетке.
     *
     * @param cell заданная клетка.
     * @return возвращает индекс фигуры в массиве figures.
     */
    private int getIndexCell(Cell cell) {
        for (int index = 0; index < position; index++) {
            if (figures[index].getPosition().x == cell.x && figures[index].getPosition().y == cell.y) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Метод реализует перемещение фигур на доске.
     *
     * @param source начальная клетка.
     * @param dest   конечная клетка.
     * @return true если перемещение успешно.
     * @throws ImpossibleMoveException Фигура не может так ходить.
     * @throws OccupiedWayException    На пути движения фигуры есть другие фигуры.
     * @throws FigureNotFoundException В начальной клетке фигура не найдена.
     */
    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure figure = checkCell(source);
        boolean result = false;
        try {
            if (figure == null) {
                throw new FigureNotFoundException();
            }
            Cell[] way = figure.way(source, dest);
            for (Cell cell : way) {
                Figure anotherFigure = checkCell(cell);
                if (anotherFigure != null) {
                    throw new OccupiedWayException();
                }
            }
            figures[getIndexCell(source)] = figure.copy(dest);
            result = true;
        } catch (FigureNotFoundException fnf) {
            System.out.println("FigureNotFoundException");
        } catch (ImpossibleMoveException ime) {
            System.out.println("ImpossibleMoveException");
        } catch (OccupiedWayException owe) {
            System.out.println("OccupiedWayException");
        }
        return result;
    }
}
