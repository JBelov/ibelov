package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 20.02.2018
 */

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        String[] expectedArray = {"Привет", "Мир", "Супер"};
        String[] inputArray = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] resultArray = ArrayDuplicate.remove(inputArray);
        assertThat(resultArray, arrayContainingInAnyOrder(expectedArray));
        }
    }


