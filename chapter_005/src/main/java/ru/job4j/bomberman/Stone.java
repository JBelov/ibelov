package ru.job4j.bomberman;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
class Stone {

    Stone(Cell position, Board board) {
        board.tryCell(position);
    }
}
