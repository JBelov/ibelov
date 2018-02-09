package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */
public class SquareTest {
    @Test
    public void whenBoundIsThree() {
    Square square = new Square();
        int[] result = square.calculate(3);
        int[] expected = new int[] {1, 4, 9};
        assertThat(result, is(expected));

    }


}