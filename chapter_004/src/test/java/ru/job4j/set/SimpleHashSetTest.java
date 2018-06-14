package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class SimpleHashSetTest {

    @Test
    public void add() {
        SimpleHashSet<String> hashSet = new SimpleHashSet<>();
        hashSet.add("zero");
        hashSet.add("one");
        hashSet.add("two");
        hashSet.add("three");
        hashSet.add("four");
        hashSet.add("five");
        assertThat(hashSet.add("five"), is(false));
        assertThat(hashSet.remove("two"), is(true));
        assertThat(hashSet.remove("two"), is(false));
        assertThat(hashSet.contains("zero"), is(true));
        assertThat(hashSet.contains("one"), is(true));
        assertThat(hashSet.contains("two"), is(false));
        assertThat(hashSet.contains("five"), is(true));
        assertThat(hashSet.contains("six"), is(false));
    }
}