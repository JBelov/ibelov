package ru.job4j.parser;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 *
 * Исключение при ошибке валидации строки.
 */

public class ValidateErrorException extends RuntimeException {

    /**
     * Позиция в строке, где найдена первая ошибка.
     */
    private int firsError;

    public ValidateErrorException(int wrongChar) {
        super("Validation error on symbol: " + wrongChar);
        this.firsError = wrongChar;
    }

    /**
     * Метод возвращает позицию первой ошибки валидации.
     * Если не все скобки закрыты, возвращается индекс последнего символа.
     * @return индекс первой ошибки валидации.
     */
    public int getFirstErrorPosition() {
        return firsError;
    }

}
