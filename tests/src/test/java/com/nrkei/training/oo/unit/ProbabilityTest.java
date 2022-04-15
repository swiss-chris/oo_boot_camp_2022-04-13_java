/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.probability.Probability;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProbabilityTest {

    @Test void rangeCheck() {
        assertThrows(IllegalArgumentException.class, () -> new Probability(1.0001));
        assertThrows(IllegalArgumentException.class, () -> new Probability(-0.0001));
        assertDoesNotThrow(() -> new Probability(1));
        assertDoesNotThrow(() -> new Probability(0));
    }

    @Test void equals() {
        assertEquals(new Probability(0.5), new Probability(0.5));
        assertEquals(new Probability(0.2), new Probability(0.2));
    }

    @Test void compareTo() {
        assertTrue(new Probability(0.6).compareTo(new Probability(0.5)) > 0);
        assertTrue(new Probability(0.4).compareTo(new Probability(0.5)) < 0);
        assertEquals(0, new Probability(0.5).compareTo(new Probability(0.5)));
    }

    @Test
    void not() {
        assertEquals(new Probability(0.2), new Probability(0.8).not());
        assertEquals(new Probability(0.8), new Probability(0.8).not().not());
        assertEquals(new Probability(0), new Probability(1).not());
        assertEquals(new Probability(1), new Probability(0).not());
    }

    @Test
    void and() {
        assertEquals(new Probability(0.4), new Probability(0.8).and(new Probability(0.5)));
        assertEquals(new Probability(0.32), new Probability(0.8).and(new Probability(0.4)));
    }

    @Test
    void or() {
        assertEquals(new Probability(0.75), new Probability(0.5).or(new Probability(0.5)));
        assertEquals(new Probability(0.68), new Probability(0.2).or(new Probability(0.6)));
    }
}
