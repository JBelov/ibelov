package ru.job4j.parser;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 *
 * Класс описывающий пары скобок.
 */

class Brackets {

    /**
     * Символ открывающей скобки.
     */
    private char type;
    /**
     * Номер символа в строке, где находится открывающая скобка.
     */
    private int open;
    /**
     * Номер символа в строке, где находится закрывающая скобка.
     */
    private int close;

    Brackets(char type, int open, int close) {
        this.type = type;
        this.open = open;
        this.close = close;
    }

    Brackets(char type, int open) {
        this.type = type;
        this.open = open;
    }

    char getType() {
        return type;
    }

    int getClose() {
        return close;
    }

    int getOpen() {
        return open;
    }

    void setType(char type) {
        this.type = type;
    }

    void setClose(int close) {
        this.close = close;
    }

    void setOpen(int open) {
        this.open = open;
    }
}
