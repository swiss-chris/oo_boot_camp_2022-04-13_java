/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

public abstract class Quantity {

    static final double EPSILON = 1e-10;

    final double amount;
    final DeltaUnit unit;

    Quantity(double amount, DeltaUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    boolean isCompatible(Quantity other) {
        return this.unit.isCompatible(other.unit);
    }
}
