package ru.job4j.test2;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Task02Test {

    @Test
    public void whenCalculateCorrect() {
        long m = 9;
        long r = 6;
        assertThat(Task02.calculate(m, r), is(1567641600L));
    }
}
