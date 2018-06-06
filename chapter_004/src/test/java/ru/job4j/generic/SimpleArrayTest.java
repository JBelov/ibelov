package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleArrayTest {

    @Test
    public void whenAddSomeTypeToContainerThanGetTheSameType() {
        SimpleArray<Integer> simpleInt = new SimpleArray<>(10);
        simpleInt.add(3);
        int result = simpleInt.get(0);

        assertThat(result, is(3));

        SimpleArray<String> simpleString = new SimpleArray<>(5);
        simpleString.add("some text");
        String result2 = simpleString.get(0);

        assertThat(result2, is("some text"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenSetTheNewElementThanWeGetItBack() {
        SimpleArray<String> simpleString = new SimpleArray<>(3);
        simpleString.add("one");
        simpleString.add("two");
        simpleString.add("three");
        simpleString.set(1, "new two");

        assertThat(simpleString.get(1), is("new two"));

        simpleString.add("four");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteOneElementAndAddAnotherThanArrayIsOk() {
        SimpleArray<String> simpleInt = new SimpleArray<>(3);
        simpleInt.add("one");
        simpleInt.add("two");
        simpleInt.add("three");
        simpleInt.delete(1);
        simpleInt.add("four");

        assertThat(simpleInt.get(0), is("one"));
        assertThat(simpleInt.get(1), is("three"));
        assertThat(simpleInt.get(2), is("four"));

        simpleInt.get(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterableInterfaceWorksFine() {
        SimpleArray<String> simpleString = new SimpleArray<>(3);
        simpleString.add("one");
        simpleString.add("two");
        simpleString.add("three");
        Iterator<String> iterator = simpleString.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(false));

        iterator.next();
    }
}