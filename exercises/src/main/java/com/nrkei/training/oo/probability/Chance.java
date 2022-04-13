/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.probability;

import java.util.Objects;

// Understands the likelihood of something specific occurring
public final class Chance {
    private static final double CERTAIN_FRACTION = 1.0;

    private final double fraction;

    public Chance(double likelihoodAsFraction) {
        fraction = likelihoodAsFraction;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof Chance && this.equals((Chance)other);
    }

    private boolean equals(Chance other) {
        return this.fraction == other.fraction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fraction);
    }

    public Chance not() {
        return new Chance(CERTAIN_FRACTION - fraction);
    }
}
