package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnTest {

    @Test
    public void whenPawnMovesCorrectly() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(1, 2);
        Pawn pawn = new Pawn(source);
        board.add(pawn);
        assertThat(board.move(source, dest), is(true));
    }

    @Test
    public void whenFigureNotFound() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(1, 2);
        Pawn pawn = new Pawn(source);
        board.add(pawn);
        assertThat(board.move(dest, source), is(false));
    }

    @Test
    public void whenQueenImpossibleMove() {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(2, 6);
        Pawn pawn = new Pawn(source);
        board.add(pawn);
        assertThat(board.move(source, dest), is(false));
    }

    @Test
    public void whenPawnMeetsBishopOnTheWay() {
        Board board = new Board();
        Cell source = new Cell(2, 2);
        Cell dest = new Cell(2, 3);
        Cell occupiedCell = new Cell(2, 3);
        Pawn pawn = new Pawn(source);
        Bishop bishop = new Bishop(occupiedCell);
        board.add(pawn);
        board.add(bishop);
        boolean result = board.move(source, dest);
        assertThat(result, is(false));
    }
}