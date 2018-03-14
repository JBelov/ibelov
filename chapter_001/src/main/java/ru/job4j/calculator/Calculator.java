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
    /**
     * Сложение.
     * @param first первое слагаемое.
     * @param second второе слагаемое.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }
    /**
     * Вычитание.
     * @param first уменьшаемое.
     * @param second вычитаемое.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }
    /**
     * Деление.
     * @param first делимое.
     * @param second делитель.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }
    /**
     * Умножение.
     * @param first первый множитель.
     * @param second второй множитель.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
    /**
     * Возврат результата.
     * @return результат.
     */
    public double getResult() {
        return this.result;
    }
}