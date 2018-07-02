package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private int xDirection = 1;

    public RectangleMove(Rectangle rect, int limitX) {
        this.limitX = limitX;
        this.rect = rect;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (this.rect.getX() + 10 == limitX) {
                this.xDirection = -1;
            }
            if (this.rect.getX() == 0) {
                this.xDirection = 1;
            }
            this.rect.setX(this.rect.getX() + xDirection);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}