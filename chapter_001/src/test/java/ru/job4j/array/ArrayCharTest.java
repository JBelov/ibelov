package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 18.02.2018
 */

public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }

    @Test
    public void whenNotContainsWordThenFalse() {
        boolean result = ArrayChar.contains("Hello", "Hi");
        assertThat(result, is(false));
    }
    @Test
    public void whenContainsWordThenTrue() {
        boolean result = ArrayChar.contains("Hello", "ll");
        assertThat(result, is(true));
    }
}