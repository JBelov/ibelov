package ru.job4j.bomberman;

import java.util.Random;

public class Bomberman extends Thread {
    private Cell position;
    private boolean alive;
    private final Board board;

    Bomberman(Cell position, Board board) {
        this.board = board;
        this.position = position;
        this.alive = true;
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
            if (board.move(position, nextCell)) {
                this.position = nextCell;
            }
        }
    }
}
