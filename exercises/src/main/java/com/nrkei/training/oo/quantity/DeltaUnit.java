/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import java.util.Objects;

public abstract class DeltaUnit extends Unit {

    final double baseUnitRatio;

    protected DeltaUnit() {
        baseUnitRatio = 1.0;
    }

    protected DeltaUnit(double relativeRatio, DeltaUnit relativeUnit) {
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }

    public DeltaQuantity s(double amount) {
        return delta(amount);
    }

    public DeltaQuantity es(double amount) {
        return s(amount);
    }

    public DeltaQuantity delta(double amount) {
        return new DeltaQuantity(amount, this);
    }

    double convertedAmount(double otherAmount, DeltaUnit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hashCode(amount * baseUnitRatio);
    }
}
