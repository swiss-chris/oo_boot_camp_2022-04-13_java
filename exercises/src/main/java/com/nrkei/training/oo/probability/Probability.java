package com.nrkei.training.oo.probability;

import java.util.Objects;

// understands the likelyhood of something occurring
public final class Probability implements Comparable<Probability>{
    private static final double EPSILON = Math.pow(10, -10);
    public static final int CERTAINTY = 1;
    private final double fraction;

    public Probability(double fraction) {
        if (fraction < 0 || fraction > 1) {
            throw new IllegalArgumentException("0..1");
        }
        this.fraction = fraction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Probability)) {
            return false;
        }
        return Math.abs(((Probability) o).fraction - fraction) < EPSILON;
    }

    public Probability not() {
        return new Probability(CERTAINTY - this.fraction);
    }

    public Probability and(Probability probability) {
        return new Probability(this.fraction * probability.fraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fraction);
    }

    @Override
    public String toString() {
        return "Probability{probability=" + fraction + '}';
    }

    @Override
    public int compareTo(Probability o) {
        final double diff = fraction - o.fraction;
        return (diff > 0) ? 1 : (diff < 0) ? -1 : 0;
    }

    public Probability or(Probability probability) {
        // de morgan's law: a OR b = !(!a AND !b)
        return this.not().and(probability.not()).not();
    }
}
