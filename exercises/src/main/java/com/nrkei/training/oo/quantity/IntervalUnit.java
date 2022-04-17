/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import java.util.Objects;

public abstract class IntervalUnit extends Unit {

    private final double offset;

    protected IntervalUnit() {
        super();
        offset = 0.0;
    }

    protected IntervalUnit(double relativeRatio, IntervalUnit relativeUnit) {
        this(relativeRatio, relativeUnit, 0.0);
    }

    protected IntervalUnit(double relativeRatio, IntervalUnit relativeUnit, double offset) {
        super(relativeRatio, relativeUnit);
        this.offset = offset;
    }

    public IntervalQuantity s(double amount) {
        return new IntervalQuantity(amount, this);
    }

    public IntervalQuantity es(double amount) {
        return s(amount);
    }

    double convertedAmount(double otherAmount, IntervalUnit other) {
        return (otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset;
    }

    int hashCode(double amount) {
        return Objects.hashCode((amount - offset) * baseUnitRatio);
    }
}
