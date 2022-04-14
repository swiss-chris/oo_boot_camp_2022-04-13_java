/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import com.nrkei.training.oo.Orderable;

public class IntervalQuantity implements Orderable<IntervalQuantity> {

    protected final double amount;
    protected final Unit unit;

    IntervalQuantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof IntervalQuantity && this.equals((IntervalQuantity) other);
    }

    private boolean equals(IntervalQuantity other) {
        return this.isCompatible(other) && this.amount == convertedAmount(other);
    }

    private boolean isCompatible(IntervalQuantity other) {
        return this.unit.isCompatible(other.unit);
    }

    protected double convertedAmount(IntervalQuantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    @Override
    public boolean isBetterThan(IntervalQuantity other) {
        return this.amount > convertedAmount(other);
    }
}
