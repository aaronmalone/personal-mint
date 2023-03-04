package com.aaronmalone.account.activity.utility;

import org.junit.jupiter.api.Test;

import static com.aaronmalone.account.activity.utility.Split.split;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitTest {
    @Test
    void testSplit() {
        String[] array = split("foo,\"bar, but also baz\",54321,fin");
        assertEquals("foo", array[0]);
        assertEquals("bar, but also baz", array[1]);
        assertEquals("54321", array[2]);
        assertEquals("fin", array[3]);
    }
}