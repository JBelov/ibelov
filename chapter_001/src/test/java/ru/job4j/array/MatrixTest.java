package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 18.02.2018
 */

public class MatrixTest {
    @Test
    public void whenSizeIsThreeThenCorrectMatrix() {
        int[][] resultArray = Matrix.multiple(3);
        int[][] expectedArray =
                {       {0, 1, 2, 3},
                        {1, 1, 2, 3},
                        {2, 2, 4, 6},
                        {3, 3, 6, 9}

                };
        assertThat(resultArray, is(expectedArray));
    }



}
