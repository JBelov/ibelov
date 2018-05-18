package ru.job4j.parser;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class ParserTest {

    /**
     * Проверяем работу парсера без аргументов, с обработкой скобок по умолчанию "([{}])".
     */
    @Test
    public void whenAllBracketsFoundByDefault() {
        String input = "a[b({c}d)e]";
        Parser test = new Parser();
        int[][] result = test.parse(input);
        int[][] expect = {{2, 4, 6}, {0, 3, 8}, {1, 1, 10}};
        assertThat(result, is(expect));
    }

    /**
     * Проверяем работу парсера с поиском только заданных скобок "([])".
     */
    @Test
    public void whenAllChosenBracketsFoundByCustom() {
        String input = "a[b({c}d)e]";
        Parser test = new Parser("([", ")]");
        int[][] result = test.parse(input);
        int[][] expect = {{0, 3, 8}, {1, 1, 10}};
        assertThat(result, is(expect));
    }

    /**
     * Проверяем обработку исключения при ошибке валидации.
     */
    @Test
    public void whenValidationErrorException() {
        String input = "a[b({c}d{)}e]";
        Parser test = new Parser("([{", ")]}");
        String mistake = "";
        try {
            test.parse(input);
        } catch (ValidateErrorException vee) {
            mistake = vee.getLocalizedMessage() + vee.getFirstErrorPosition();
        }
        String expect = "Validation error on symbol: 9" + 9;
        assertThat(mistake, is(expect));
    }
}
