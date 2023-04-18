package com.aaronmalone.account.activity.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.aaronmalone.account.activity.utility.Split.split;

class SplitTest {
    @Test
    void testSplit() {
        CharSequence[] array = split("foo,\"bar, but also baz\",54321,fin");
        assertEquals("foo", array[0]);
        assertEquals("bar, but also baz", array[1]);
        assertEquals("54321", array[2]);
        assertEquals("fin", array[3]);
    }

    private void assertEquals(String expected, CharSequence actual) {
        Assertions.assertEquals(expected, actual.toString());
    }
}