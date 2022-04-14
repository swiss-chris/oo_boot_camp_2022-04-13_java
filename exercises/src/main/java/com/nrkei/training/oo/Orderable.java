/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo;

// Understands a specific sequence of objects
public interface Orderable<T> {
    boolean isBetterThan(T other);

    static <S extends Orderable<S>> S bestOrNull(S ... challengers) {
        S champion = null;
        for (S challenger : challengers) {
            if (champion == null || challenger.isBetterThan(champion)) champion = challenger;
        }
        return champion;
    }
}
