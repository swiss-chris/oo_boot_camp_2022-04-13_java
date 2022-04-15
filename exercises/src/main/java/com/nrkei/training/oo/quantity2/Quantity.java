/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity2;

public final class Quantity {

    private final double amount;
    private final Unit unit;
    private static final double EPSILON = 1e-10;

    Quantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof Quantity && this.equals((Quantity) other);
    }

    private boolean equals(Quantity other) {
        return this.isCompatible(other) && Math.abs(this.amount - convertedAmount(other)) < EPSILON;
    }

    private boolean isCompatible(Quantity other) {
        return this.unit.isCompatible(other.unit);
    }

    private double convertedAmount(Quantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    public Quantity plus(Quantity other) {
        return new Quantity(this.amount + convertedAmount(other), this.unit);
    }

    public Quantity negate() {
        return new Quantity(-amount, unit);
    }

    public Quantity minus(Quantity other) {
        return this.plus(other.negate());
    }

    @Override
    public String toString() {
        final double baseUnitAmount = this.unit.baseUnitAmount(this.amount, this.unit);
        return "baseUnitAmount: " + baseUnitAmount + ".";
    }
}
