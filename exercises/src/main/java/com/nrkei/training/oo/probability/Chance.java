/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.probability;

import com.nrkei.training.oo.Orderable;

import java.util.Objects;

// Understands the likelihood of something specific occurring
public final class Chance implements Orderable<Chance> {
    private static final double EPSILON = 1e-10;
    private static final double CERTAIN_FRACTION = 1.0;

    private final double fraction;

    public Chance(double likelihoodAsFraction) {
        if (likelihoodAsFraction < 0.0 || likelihoodAsFraction > 1.0)
            throw new IllegalArgumentException("Likelihood must be between 0.0 and 1.0, inclusive");
        fraction = likelihoodAsFraction;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof Chance && this.equals((Chance) other);
    }

    private boolean equals(Chance other) {
        return Math.abs(this.fraction - other.fraction) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(fraction / EPSILON));
    }

    public Chance not() {
        return new Chance(CERTAIN_FRACTION - fraction);
    }

    public Chance and(Chance other) {
        return new Chance(this.fraction * other.fraction);
    }

    // Implemented with DeMorgan's Law https://en.wikipedia.org/wiki/De_Morgan%27s_laws
    public Chance or(Chance other) {
        return this.not().and(other.not()).not();
    }

    @Override
    public boolean isBetterThan(Chance other) {
        return this.fraction < other.fraction;
    }
}
