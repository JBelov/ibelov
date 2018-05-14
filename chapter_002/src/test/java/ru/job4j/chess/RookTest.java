package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.Bishop;
import ru.job4j.chess.figures.Rook;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void whenRookMovesCorrectly() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(1, 6);
        Rook rook = new Rook(source);
        board.add(rook);
        assertThat(board.move(source, dest), is(true));
    }

    @Test
    public void whenRookMovesThreeTimes() {
        Board board = new Board();
        Cell source = new Cell(5, 5);
        Cell dest = new Cell(1, 5);
        Cell dest2 = new Cell(1, 1);
        Cell dest3 = new Cell(4, 1);
        Rook rook = new Rook(source);
        board.add(rook);
        board.move(source, dest);
        board.move(dest, dest2);
        assertThat(board.move(dest2, dest3), is(true));
    }

    @Test
    public void whenFigureNotFound() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(5, 5);
        Rook rook = new Rook(source);
        board.add(rook);
        assertThat(board.move(dest, source), is(false));
    }

    @Test
    public void whenBishopImpossibleMove() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(2, 6);
        Rook rook = new Rook(source);
        board.add(rook);
        assertThat(board.move(source, dest), is(false));
    }

    @Test
    public void whenRookMeetsBishopOnTheWay() {
        Board board = new Board();
        Cell source = new Cell(1, 7);
        Cell dest = new Cell(1, 1);
        Cell occupiedCell = new Cell(1, 4);
        Rook rook = new Rook(source);
        Bishop bishop = new Bishop(occupiedCell);
        board.add(rook);
        board.add(bishop);
        boolean result = board.move(source, dest);
        assertThat(result, is(false));
    }
}