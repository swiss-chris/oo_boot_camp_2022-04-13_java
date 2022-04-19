/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// Ensures Graph algorithms work correctly
class GraphTest {

    private final static Node A, B, C, D, E, F, G;

    static {

        A = new Node();
        B = new Node();
        C = new Node();
        D = new Node();
        E = new Node();
        F = new Node();
        G = new Node();

        B.to(A);
        B.to(C).to(D).to(E).to(B).to(F);
        C.to(D);
        C.to(E);
    }

    private static final double EPSILON = 1e-10;

    @Test void canReach() {
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(A));
        assertTrue(B.canReach(F));
        assertTrue(B.canReach(D));
        assertTrue(C.canReach(F));
        assertFalse(G.canReach(B));
        assertFalse(A.canReach(B));
        assertFalse(B.canReach(G));
    }

    @Test void hopCount() {
        assertEquals(0, B.hopCount(B));
        assertEquals(1, B.hopCount(A));
        assertEquals(1, B.hopCount(F));
        assertEquals(2, B.hopCount(D));
        assertEquals(3, C.hopCount(F));
        assertThrows(IllegalArgumentException.class, () -> G.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> A.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> B.hopCount(G));
    }
}
