package ru.job4j.calculator;

/**
 * Calculate.
 *
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 19.01.2018
 */

public class Calculator {
    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    public void div(double first, double second) {
        this.result = first / second;
    }

    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }
}