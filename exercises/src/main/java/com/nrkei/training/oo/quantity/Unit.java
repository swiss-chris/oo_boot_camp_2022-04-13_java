/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import java.util.Objects;

public abstract class Unit {

    final double baseUnitRatio;

    protected Unit() {
        baseUnitRatio = 1.0;
    }

    protected Unit(double relativeRatio, Unit relativeUnit) {
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }

    boolean isCompatible(Unit other) {
        return this.getClass() == other.getClass();
    }

    double convertedAmount(double otherAmount, Unit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    public DeltaQuantity delta(double amount) {
        return new DeltaQuantity(amount, this);
    }

    int hashCode(double amount) {
        return Objects.hashCode(amount * baseUnitRatio);
    }
}
