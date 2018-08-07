package ru.job4j.bomberman;

import java.util.Random;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Monster extends Thread {
    private Cell position;
    private boolean alive;
    private final Board board;
    private final String name;

    Monster(Cell position, Board board, String name) {
        this.board = board;
        this.position = position;
        this.alive = true;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Random random = new Random();
        board.tryCell(position);
        while (alive) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cell nextCell = new Cell(position.getX() + (random.nextInt(2) - 1),
                    position.getY() + random.nextInt(2) - 1);
            if (board.move(position, nextCell, name)) {
                this.position = nextCell;
            }
        }
    }
}