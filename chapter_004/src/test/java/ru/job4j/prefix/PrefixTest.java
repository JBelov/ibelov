package ru.job4j.prefix;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class PrefixTest {

    @Test
    public void find() {
        Prefix tester = new Prefix();
        String[] input = {"кот", "котейка", "котик", "синхрофазотрон"};
        assertThat(tester.find(input), is("котейка"));
    }
}