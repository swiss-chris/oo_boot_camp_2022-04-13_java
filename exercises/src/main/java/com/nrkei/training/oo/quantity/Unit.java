/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

// C -> F: /5 *9 +32
// F -> C: -32 /9 *5\

// 0C -> 32F: +40 /5 *9 (72) -40 (32)
// 32F -> 0C: +40 /9 *5 -40 (0)



import java.util.Objects;

public final class Unit {
    public static final Unit TEASPOON = new Unit();
    public static final Unit TABLESPOON = new Unit(3, TEASPOON);
    public static final Unit OUNCE = new Unit(2, TABLESPOON);
    public static final Unit CUP = new Unit(8, OUNCE);
    public static final Unit PINT = new Unit(2, CUP);
    public static final Unit QUART = new Unit(2, PINT);
    public static final Unit GALLON = new Unit(4, QUART);

    public static final Unit INCH = new Unit();
    public static final Unit FOOT = new Unit(12, INCH);
    public static final Unit YARD = new Unit(3, FOOT);
    public static final Unit CHAIN = new Unit(22, YARD);
    public static final Unit FURLONG = new Unit(10, CHAIN);
    public static final Unit MILE = new Unit(8, FURLONG);

    public static final Unit CELSIUS = new Unit();
    public static final Unit FAHRENHEIT = new Unit(5/9.0, 32, CELSIUS);

    private final Unit baseUnit;
    private final double baseUnitRatio;
    private final double offset;

    private Unit() {
        baseUnit = this;
        baseUnitRatio = 1.0;
        offset = 0.0;
    }

    private Unit(double relativeRatio, Unit relativeUnit) {
        this(relativeRatio, 0.0, relativeUnit);
    }

    private Unit(double relativeRatio, double offset, Unit relativeUnit) {
        this.offset = offset;
        baseUnit = relativeUnit.baseUnit;
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;

    }

    public Quantity s(double amount) {
        return new Quantity(amount, this);
    }

    public Quantity es(double amount) {
        return s(amount);
    }

    public double convertedDeltaAmount(double otherAmount, Unit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    double convertedAmount(double otherAmount, Unit other) {
        if (!this.isCompatible(other)) throw new IllegalArgumentException("Incompatible Units");
        return baseUnitAmount(otherAmount, other) / this.baseUnitRatio + this.offset;
    }

    double baseUnitAmount(double otherAmount, Unit other) {
        return (otherAmount - other.offset) * other.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hashCode((amount - offset) * baseUnitRatio);
    }

    boolean isCompatible(Unit other) {
        return this.baseUnit == other.baseUnit;
    }
}
