package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */
public class FindLoopTest {
    @Test
    public void whenElementIsThreeIndexIsTwo() {
        int[] input = new int[] {1, 2, 3};
        int result = FindLoop.indexOf(input, 3);
        assertThat(result, is(2));
    }
    @Test
    public void whenElementIsFourReturnMinusOne() {
        int[] input = new int[] {1, 2, 3};
        int result = FindLoop.indexOf(input, 4);
        assertThat(result, is(-1));
    }


}