/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import java.util.Objects;

public abstract class DeltaUnit {

    final double baseUnitRatio;

    protected DeltaUnit() {
        baseUnitRatio = 1.0;
    }

    protected DeltaUnit(double relativeRatio, DeltaUnit relativeUnit) {
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }

    public DeltaQuantity delta(double amount) {
        return new DeltaQuantity(amount, this);
    }

    boolean isCompatible(DeltaUnit other) {
        return this.getClass() == other.getClass();
    }

    double convertedAmount(double otherAmount, DeltaUnit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hashCode(amount * baseUnitRatio);
    }
}
