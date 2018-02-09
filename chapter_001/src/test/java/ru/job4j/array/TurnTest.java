package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 09.02.2018
 */
public class TurnTest {
    @Test
    public void whenArrayIsOneTwoThreeReturnThreeTwoOne() {
        int[] inputArray = {1, 2, 3};
        int[] resultArray = Turn.back(inputArray);
        int[] expectedArray = {3, 2, 1};
        assertThat(resultArray, is(expectedArray));
    }
    @Test
    public void whenArrayIsOneTwoThreeFourReturnFourThreeTwoOne() {
        int[] inputArray = {1, 2, 3, 4};
        int[] resultArray = Turn.back(inputArray);
        int[] expectedArray = {4, 3, 2, 1};
        assertThat(resultArray, is(expectedArray));
    }


}
