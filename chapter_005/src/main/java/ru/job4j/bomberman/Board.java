package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    private final ReentrantLock[][] board;
    private final int xSize;
    private final int ySize;
    private AtomicInteger xShift;
    private AtomicInteger yShift;


    /**
     * Board initialization.
     */
    Board(int xSize, int ySize, int monsters, int stones) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.xShift = new AtomicInteger(0);
        this.yShift = new AtomicInteger(0);
        this.board = new ReentrantLock[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                board[x][y] = new ReentrantLock();
            }
        }
        Random random = new Random();
        this.addBomberman(new Cell(
                random.nextInt(xSize),
                random.nextInt(ySize)));
        for (int i = 0; i < monsters; i++) {
            this.addMonster(
                    new Cell(
                            random.nextInt(xSize),
                            random.nextInt(ySize)
                    ),
                    "Monster #" + i);
        }
        for (int i = 0; i < stones; i++) {
            int x = random.nextInt(xSize);
            int y = random.nextInt(ySize);
            this.addStone(new Cell(x, y));
            System.out.println("Stone placed at " + x + " " + y);
        }
    }

    /**
     * Adding new Bomberman on the Board.
     *
     * @param cell cell to place the Unit.
     */
    private boolean addBomberman(Cell cell) {
        new Bomberman(cell, this);
        return true;
    }

    /**
     * Adding new Monster on the Board.
     *
     * @param cell cell to place the Unit.
     */
    private boolean addMonster(Cell cell, String name) {
        new Monster(cell, this, name);
        return true;
    }

    /**
     * Adding new Stone on the Board.
     *
     * @param cell cell to place the Unit.
     */
    private boolean addStone(Cell cell) {
        new Stone(cell, this);
        return true;
    }

    /**
     * Move the Unit.
     *
     * @param source source cell.
     * @param dest   destination cell.
     * @return true if movement successful.
     */
    boolean move(Cell source, Cell dest, String name) {
        boolean result = false;
        if (dest.getX() >= 0 && dest.getX() < this.xSize && dest.getY() >= 0 && dest.getY() < this.ySize
                && tryCell(dest)) {
            result = true;
            System.out.println(name + " Moved to " + dest.getX() + " " + dest.getY());
            board[source.getX()][source.getY()].unlock();
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
                    && this.board[cell.getX()][cell.getY()].tryLock(100, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getxShift() {
        return xShift.getAndSet(0);
    }

    public void setxShift(int xShift) {
        this.xShift.set(xShift);

    }

    public int getyShift() {
        return yShift.getAndSet(0);
    }

    public void setyShift(int yShift) {
        this.yShift.set(yShift);
    }
}
