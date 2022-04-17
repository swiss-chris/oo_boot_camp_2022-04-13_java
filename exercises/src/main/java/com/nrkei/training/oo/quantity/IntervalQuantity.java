/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

public final class IntervalQuantity extends Quantity {

    IntervalQuantity(double amount, IntervalUnit unit) {
        super(amount, unit);
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof IntervalQuantity && this.equals((IntervalQuantity) other);
    }

    private boolean equals(IntervalQuantity other) {
        return this.isCompatible(other) && Math.abs(this.amount - convertedAmount(other)) < EPSILON;
    }

    double convertedAmount(IntervalQuantity other) {
        return ((IntervalUnit) unit).convertedAmount(other.amount, (IntervalUnit) other.unit);
    }

    public IntervalQuantity plus(DeltaQuantity other) {
        return ((IntervalUnit) this.unit).s(this.amount + this.unit.convertedAmount(other.amount, other.unit));
    }

    public IntervalQuantity minus(DeltaQuantity other) {
        return this.plus(other.negate());
    }

    @Override
    public int hashCode() {
        return (unit).hashCode(amount);
    }
}
