package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingTest {

    @Test
    public void whenKingMovesCorrectly() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 4);
        King king = new King(source);
        board.add(king);
        assertThat(board.move(source, dest), is(true));
    }

    @Test
    public void whenQueenMovesThreeTimes() {
        Board board = new Board();
        Cell source = new Cell(5, 5);
        Cell dest = new Cell(6, 6);
        Cell dest2 = new Cell(6, 7);
        Cell dest3 = new Cell(5, 6);
        King king = new King(source);
        board.add(king);
        board.move(source, dest);
        board.move(dest, dest2);
        assertThat(board.move(dest2, dest3), is(true));
    }

    @Test
    public void whenFigureNotFound() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(2, 2);
        King king = new King(source);
        board.add(king);
        assertThat(board.move(dest, source), is(false));
    }

    @Test
    public void whenKingImpossibleMove() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(2, 6);
        King king = new King(source);
        board.add(king);
        assertThat(board.move(source, dest), is(false));
    }

    @Test
    public void whenKingMeetsBishopOnHisWay() {
        Board board = new Board();
        Cell source = new Cell(7, 7);
        Cell dest = new Cell(6, 6);
        Cell occupiedCell = new Cell(6, 6);
        King king = new King(source);
        Bishop bishop = new Bishop(occupiedCell);
        board.add(king);
        board.add(bishop);
        boolean result = board.move(source, dest);
        assertThat(result, is(false));
    }
}