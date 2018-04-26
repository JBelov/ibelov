package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CombineArraysTest {
    @Test
    public void whenCombineTwoArraysResultIsSorted() {
        int[] firstArray = {1,3,4,6,8,9};
        int[] secondArray = {2,4,5,7,10};
        int[] resultArray = CombineArrays.combine(firstArray, secondArray);
        int[] expectedArray = {1,2,3,4,4,5,6,7,8,9,10};
        assertThat(resultArray, is(expectedArray));
    }
}