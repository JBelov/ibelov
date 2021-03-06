package ru.job4j.bomberman;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Bomberman extends Thread {
    private Cell position;
    private boolean alive;
    private final Board board;
    private final String name = "Bomberman";

    Bomberman(Cell position, Board board) {
        this.board = board;
        this.position = position;
        this.alive = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        board.tryCell(position);
        while (alive) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cell nextCell = new Cell(
                    position.getX() + board.getxShift(),
                    position.getY() + board.getyShift()
            );
            if (board.move(position, nextCell, name)) {
                this.position = nextCell;
            }
        }
    }
}
