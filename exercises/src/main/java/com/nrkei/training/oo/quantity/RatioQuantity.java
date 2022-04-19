/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

public final class RatioQuantity {

    private final double amount;
    private final RatioUnit ratioUnit;

    RatioQuantity(double amount, RatioUnit ratioUnit) {
        this.amount = amount;
        this.ratioUnit = ratioUnit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof RatioQuantity && this.equals((RatioQuantity) other);
    }

    private boolean equals(RatioQuantity other) {
        return this.isCompatible(other) && this.amount == convertedAmount(other);
    }

    private boolean isCompatible(RatioQuantity other) {
        return this.ratioUnit.isCompatible(other.ratioUnit);
    }

    private double convertedAmount(RatioQuantity other) {
        return this.ratioUnit.convertedAmount(other.amount, other.ratioUnit);
    }

    @Override
    public int hashCode() {
        return ratioUnit.hashCode(amount);
    }

    public RatioQuantity plus(RatioQuantity other) {
        return new RatioQuantity(this.amount + convertedAmount(other), this.ratioUnit);
    }

    public RatioQuantity negate() {
        return new RatioQuantity(-amount, ratioUnit);
    }

    public RatioQuantity minus(RatioQuantity other) {
        return this.plus(other.negate());
    }

    @Override
    public String toString() {
        return "RatioQuantity{" +
                "amount=" + this.ratioUnit.baseUnit.convertedAmount(this.amount, this.ratioUnit)+
                '}';
    }
}
