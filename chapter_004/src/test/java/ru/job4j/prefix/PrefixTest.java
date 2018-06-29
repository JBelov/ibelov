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
    public void whenKoteykaFound() {
        Prefix tester = new Prefix();
        String[] input = {"кот", "котейка", "котик", "синхрофазотрон", "сила"};
        assertThat(tester.find(input, 3), is("котейка"));
    }

    @Test
    public void whenSynchrophasotronFound() {
        Prefix tester = new Prefix();
        String[] input = {"кот", "котейка", "котик", "синхрофазотрон", "сила"};
        assertThat(tester.find(input, 2), is("синхрофазотрон"));
    }
}