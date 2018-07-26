package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private final int xSize;
    private final int ySize;

    /**
     * Board initialization.
     */
    Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        board = new ReentrantLock[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                board[x][y] = new ReentrantLock();
            }
        }
    }

    /**
     * Adding new Bomberman on the Board.
     *
     * @param cell cell to place the Unit.
     */
    boolean addBomberman(Cell cell) {
        new Bomberman(cell, this);
        return true;
    }

    /**
     * Move the Unit.
     *
     * @param source source cell.
     * @param dest   destination cell.
     * @return true if movement successful.
     */
    boolean move(Cell source, Cell dest) {
        boolean result = false;
        try {
            if (dest.getX() >= 0 && dest.getX() <= this.xSize && dest.getY() >= 0 && dest.getY() <= this.ySize
                    && this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                result = true;
                System.out.println(Thread.currentThread().getId() + " Moved to " + dest.getX() + " " + dest.getY());
                board[source.getX()][source.getY()].unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Try to occupy some cell.
     *
     * @param cell cell to be occupied.
     * @return true in case of success.
     */
    public boolean tryCell(Cell cell) {
        try {
            return cell.getX() >= 0 && cell.getX() <= this.xSize && cell.getY() >= 0 && cell.getY() <= this.ySize
                    && this.board[cell.getX()][cell.getY()].tryLock(500, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
