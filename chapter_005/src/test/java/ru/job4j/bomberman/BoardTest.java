package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    @Test
    public void bombermanTest() throws InterruptedException {
        Board board = new Board(15, 10, 3, 4);
        for (int i = 1; i < 10; i++) {
            Thread.sleep(500);
            board.setxShift(1);
            board.setyShift(1);
        }
        for (int i = 1; i < 10; i++) {
            Thread.sleep(500);
            board.setxShift(-1);
            board.setyShift(-1);
        }
    }
}