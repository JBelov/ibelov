package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {

    @Test
    public void whenKnightMovesCorrectly() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(3, 2);
        Knight knight = new Knight(source);
        board.add(knight);
        assertThat(board.move(source, dest), is(true));
    }

    @Test
    public void whenKnightMovesThreeTimes() {
        Board board = new Board();
        Cell source = new Cell(5, 5);
        Cell dest = new Cell(7, 4);
        Cell dest2 = new Cell(5, 3);
        Cell dest3 = new Cell(7, 2);
        Knight knight = new Knight(source);
        board.add(knight);
        board.move(source, dest);
        board.move(dest, dest2);
        assertThat(board.move(dest2, dest3), is(true));
    }

    @Test
    public void whenFigureNotFound() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(3, 2);
        Queen queen = new Queen(source);
        board.add(queen);
        assertThat(board.move(dest, source), is(false));
    }

    @Test
    public void whenKnightImpossibleMove() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(2, 6);
        Knight knight = new Knight(source);
        board.add(knight);
        assertThat(board.move(source, dest), is(false));
    }

    @Test
    public void whenKnightMeetsBishopOnTheEndOfHisWay() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(3, 2);
        Cell occupiedCell = new Cell(3, 2);
        Knight knight = new Knight(source);
        Bishop bishop = new Bishop(occupiedCell);
        board.add(knight);
        board.add(bishop);
        boolean result = board.move(source, dest);
        assertThat(result, is(false));
    }

    @Test
    public void whenKnightJumpOverBishop() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(3, 2);
        Cell occupiedCell = new Cell(2, 1);
        Knight knight = new Knight(source);
        Bishop bishop = new Bishop(occupiedCell);
        board.add(knight);
        board.add(bishop);
        boolean result = board.move(source, dest);
        assertThat(result, is(true));
    }
}