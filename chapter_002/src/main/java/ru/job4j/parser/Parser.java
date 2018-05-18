package ru.job4j.parser;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 *
 * Класс реализует парсер скобок в заданной строке. Есть возможность задавать типы скобок, требующие проверки.
 */

public class Parser {

    /**
     * Строки, содержащие последовательность соответствующих открывающих/закрывающих скобок.
     */
    private String opens;
    private String closes;

    /**
     * Конструктор по умолчанию, обрабатываются скобки "([{}])".
     */
    public Parser() {
        this.opens = "([{";
        this.closes = ")]}";
    }

    /**
     * Конструктор принимающий строки с последовательностями скобок для проверки.
     * @param opens Открывающие скобки вида String "[откр1][откр2]...[откр(n)]"
     * @param closes Соответствующие закрывающие скобки вида String "[закр1][закр2]...[закр(n)]"
     */
    public Parser(String opens, String closes) {
        this.opens = opens;
        this.closes = closes;
    }

    /**
     * Метод реализует проверку входящей строки на правильное расположение скобок и возвращает их расположение.
     * @param input Входящая строка для проверки.
     * @return Массив расположения скобок int[скобок в строке(n)][кол-во пар проверяемых скобок]
     * {{[индекс типа скобки1],[позиция отк. скобки1],[позиция закр. скобки1]},{}...{n}}
     * @throws ValidateErrorException исключение выбрасывается в случае ошибки валидации строки.
     *
     */
    public int[][] parse(String input) throws ValidateErrorException {
        ArrayDeque<Brackets> stack = new ArrayDeque<>();
        ArrayList<Brackets> brackets = new ArrayList<>();
        for (int index = 0; index < input.length(); index++) {
            char chCurrent = input.charAt(index);
            if (opens.indexOf(chCurrent) >= 0) {
                stack.addFirst(new Brackets(chCurrent, index));
            } else {
                if ((closes.indexOf(chCurrent) >= 0)) {
                    if (!stack.isEmpty()) {
                        Brackets closedBracket = stack.poll();
                        char chClosed = closedBracket.getType();
                        char chProperClosed = closes.charAt(opens.indexOf(chClosed));
                        if (chCurrent != chProperClosed) {
                            throw new ValidateErrorException(index);
                        }
                        closedBracket.setClose(index);
                        brackets.add(closedBracket);
                    }
                }
            }
            if ((index == input.length() - 1) && !stack.isEmpty()) {
                System.out.println(stack);
                throw new ValidateErrorException(index);
            }
        }
        int[][] result = new int[brackets.size()][3];
        for (int index = 0; index < brackets.size(); index++) {
            result [index][0] = opens.indexOf(brackets.get(index).getType());
            result [index][1] = brackets.get(index).getOpen();
            result [index][2] = brackets.get(index).getClose();
        }
        return result;
    }
}
