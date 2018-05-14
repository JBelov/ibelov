package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.Bishop;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopTest {

    @Test
    public void whenBishopMovesCorrectly() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(5, 5);
        Bishop bishop = new Bishop(source);
        board.add(bishop);
        assertThat(board.move(source, dest), is(true));
    }

    @Test
    public void whenBishopMovesThreeTimes() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(3, 3);
        Cell dest2 = new Cell(5, 1);
        Cell dest3 = new Cell(1, 5);
        Bishop bishop = new Bishop(source);
        board.add(bishop);
        board.move(source, dest);
        board.move(dest, dest2);
        assertThat(board.move(dest2, dest3), is(true));
    }

    @Test
    public void whenFigureNotFound() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(5, 5);
        Bishop bishop = new Bishop(source);
        board.add(bishop);
        assertThat(board.move(dest, source), is(false));
    }

    @Test
    public void whenBishopImpossibleMove() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(6, 7);
        Bishop bishop = new Bishop(source);
        board.add(bishop);
        assertThat(board.move(source, dest), is(false));
    }

    @Test
    public void whenBishopMeetsAnotherBishopOnHisWay() {
        Board board = new Board();
        Cell source = new Cell(7, 7);
        Cell dest = new Cell(1, 1);
        Cell occupiedCell = new Cell(3, 3);
        Bishop bishop = new Bishop(source);
        Bishop bishop2 = new Bishop(occupiedCell);
        board.add(bishop);
        board.add(bishop2);
        boolean result = board.move(source, dest);
        assertThat(result, is(false));
    }
}