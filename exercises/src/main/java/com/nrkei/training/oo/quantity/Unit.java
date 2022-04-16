/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import java.util.Objects;

public abstract class Unit {

    private final double baseUnitRatio;
    private final double offset;

    Unit() {
        baseUnitRatio = 1.0;
        offset = 0.0;
    }

    Unit(double relativeRatio, Unit relativeUnit) {
        this(relativeRatio, relativeUnit, 0.0);
    }

    Unit(double relativeRatio, Unit relativeUnit, double offset) {
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
        this.offset = offset;

    }

    public Quantity s(double amount) {
        return new Quantity(amount, this);
    }

    public Quantity es(double amount) {
        return s(amount);
    }

    double convertedDeltaAmount(double otherAmount, Unit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    double convertedAmount(double otherAmount, Unit other) {
        return baseUnitAmount(otherAmount, other) / this.baseUnitRatio + this.offset;
    }

    static double baseUnitAmount(double amount, Unit unit) {
        return (amount - unit.offset) * unit.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hashCode((amount - offset) * baseUnitRatio);
    }

    boolean isCompatible(Unit other) {
        return this.getClass() == other.getClass();
    }
}
