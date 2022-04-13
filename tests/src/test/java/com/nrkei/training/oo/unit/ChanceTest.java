/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.probability.Chance;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Chance works correctly
public class ChanceTest {

    @Test void equality() {
        assertEquals(new Chance(0.75), new Chance(0.75));
        assertNotEquals(new Chance(0.75), new Chance(0.25));
        assertNotEquals(new Chance(0.75), new Object());
        assertNotEquals(new Chance(0.75), null);
    }

    @Test void setOperations() {
        assertTrue(new HashSet<>(Collections.singletonList(new Chance(0.75))).contains(new Chance(0.75)));
        assertEquals(1, new HashSet<>(Arrays.asList(new Chance(0.75), new Chance(0.75))).size());
    }

    @Test void hash() {
        assertEquals(new Chance(0.75).hashCode(), new Chance(0.75).hashCode());
    }
}
