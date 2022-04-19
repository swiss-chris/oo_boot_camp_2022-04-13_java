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

        B.cost(5).to(A);
        B.cost(6).to(C).cost(7).to(D).cost(2).to(E).cost(3).to(B).cost(4).to(F);
        C.cost(1).to(D);
        C.cost(8).to(E);
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

    @Test void cost() {
        assertEquals(0, B.cost(B));
        assertEquals(5, B.cost(A));
        assertEquals(4, B.cost(F));
        assertEquals(7, B.cost(D));
        assertEquals(10, C.cost(F));
        assertThrows(IllegalArgumentException.class, () -> G.cost(B));
        assertThrows(IllegalArgumentException.class, () -> A.cost(B));
        assertThrows(IllegalArgumentException.class, () -> B.cost(G));
    }
}
