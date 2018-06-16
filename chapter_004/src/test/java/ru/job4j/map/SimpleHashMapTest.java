package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test(expected = ConcurrentModificationException.class)
    public void insert() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();

        map.insert("first", "one");
        map.insert("second", "two");
        map.insert("second", "TWO!");
        map.insert("third", "three");
        map.insert("fourth", "four");
        map.insert("fifth", "five");
        map.insert("sixth", "six");
        assertThat(map.get("second"), is("two"));
        assertThat(map.delete("second"), is(true));
        assertThat(map.delete("second"), is(false));
        assertThat(map.get("sixth"), is("six"));
        Iterator<String> itr = map.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("three"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("one"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("six"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("five"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("four"));
        assertThat(itr.hasNext(), is(false));
        map.insert("seventh", "seven");
        itr.hasNext();
    }
}