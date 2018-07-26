package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void twoBombermenOnTheBoard() throws InterruptedException {
        Board board = new Board(10, 10);
        board.addBomberman(new Cell(5, 5));
        board.addBomberman(new Cell(6, 6));
        Thread.sleep(10000);
    }
}