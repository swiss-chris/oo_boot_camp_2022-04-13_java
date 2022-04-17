/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

public class DeltaQuantity {

    static final double EPSILON = 1e-10;

    final double amount;
    final Unit unit;

    DeltaQuantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof DeltaQuantity && this.equals((DeltaQuantity) other);
    }

    private boolean equals(DeltaQuantity other) {
        return this.isCompatible(other) && Math.abs(this.amount - convertedAmount(other)) < EPSILON;
    }

    boolean isCompatible(DeltaQuantity other) {
        return this.unit.isCompatible(other.unit);
    }

    private double convertedAmount(DeltaQuantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    public DeltaQuantity plus(DeltaQuantity other) {
        return this.unit.delta(this.amount + this.unit.convertedAmount(other.amount, other.unit));
    }

    public DeltaQuantity negate() {
        return new DeltaQuantity(-amount, unit);
    }

    public DeltaQuantity minus(DeltaQuantity other) {
        return this.plus(other.negate());
    }
}
