package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenTest {

    @Test
    public void whenQueenMovesCorrectly() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(6, 6);
        Queen queen = new Queen(source);
        board.add(queen);
        assertThat(board.move(source, dest), is(true));
    }

    @Test
    public void whenQueenMovesThreeTimes() {
        Board board = new Board();
        Cell source = new Cell(5, 5);
        Cell dest = new Cell(1, 5);
        Cell dest2 = new Cell(5, 1);
        Cell dest3 = new Cell(3, 1);
        Queen queen = new Queen(source);
        board.add(queen);
        board.move(source, dest);
        board.move(dest, dest2);
        assertThat(board.move(dest2, dest3), is(true));
    }

    @Test
    public void whenFigureNotFound() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(5, 5);
        Queen queen = new Queen(source);
        board.add(queen);
        assertThat(board.move(dest, source), is(false));
    }

    @Test
    public void whenQueenImpossibleMove() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(2, 6);
        Queen queen = new Queen(source);
        board.add(queen);
        assertThat(board.move(source, dest), is(false));
    }

    @Test
    public void whenQueenMeetsBishopOnHerWay() {
        Board board = new Board();
        Cell source = new Cell(7, 7);
        Cell dest = new Cell(1, 1);
        Cell occupiedCell = new Cell(4, 4);
        Queen queen = new Queen(source);
        Bishop bishop = new Bishop(occupiedCell);
        board.add(queen);
        board.add(bishop);
        boolean result = board.move(source, dest);
        assertThat(result, is(false));
    }
}