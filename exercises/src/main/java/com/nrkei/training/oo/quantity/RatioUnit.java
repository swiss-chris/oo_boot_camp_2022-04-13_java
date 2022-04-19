/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

import java.util.Objects;

public final class RatioUnit {
    public static final RatioUnit TEASPOON = new RatioUnit();
    public static final RatioUnit TABLESPOON = new RatioUnit(3, TEASPOON);
    public static final RatioUnit OUNCE = new RatioUnit(2, TABLESPOON);
    public static final RatioUnit CUP = new RatioUnit(8, OUNCE);
    public static final RatioUnit PINT = new RatioUnit(2, CUP);
    public static final RatioUnit QUART = new RatioUnit(2, PINT);
    public static final RatioUnit GALLON = new RatioUnit(4, QUART);

    public static final RatioUnit INCH = new RatioUnit();
    public static final RatioUnit FOOT = new RatioUnit(12, INCH);
    public static final RatioUnit YARD = new RatioUnit(3, FOOT);
    public static final RatioUnit CHAIN = new RatioUnit(22, YARD);
    public static final RatioUnit FURLONG = new RatioUnit(10, CHAIN);
    public static final RatioUnit MILE = new RatioUnit(8, FURLONG);

    public static final RatioUnit CELSIUS = new RatioUnit();
    public static final RatioUnit FAHRENHEIT = new RatioUnit(5/9.0, CELSIUS);

    final RatioUnit baseUnit;
    final double baseUnitRatio;

    private RatioUnit() {
        baseUnit = this;
        baseUnitRatio = 1.0;
    }

    private RatioUnit(double relativeRatio, RatioUnit relativeUnit) {
        baseUnit = relativeUnit.baseUnit;
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }

    public RatioQuantity s(double amount) {
        return new RatioQuantity(amount, this);
    }

    public RatioQuantity es(double amount) {
        return s(amount);
    }

    double convertedAmount(double otherAmount, RatioUnit other) {
        if (!this.isCompatible(other)) throw new IllegalArgumentException("Incompatible Units");
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hashCode(amount * baseUnitRatio);
    }

    boolean isCompatible(RatioUnit other) {
        return this.baseUnit == other.baseUnit;
    }
}
