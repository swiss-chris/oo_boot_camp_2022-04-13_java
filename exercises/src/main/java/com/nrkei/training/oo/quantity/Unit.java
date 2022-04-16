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

public abstract class Unit {
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
    public static final RatioUnit FATHOM = new RatioUnit(6, FOOT);
    public static final RatioUnit CHAIN = new RatioUnit(22, YARD);
    public static final RatioUnit LINK = new RatioUnit(1/100.0, CHAIN);
    public static final RatioUnit FURLONG = new RatioUnit(10, CHAIN);
    public static final RatioUnit MILE = new RatioUnit(8, FURLONG);
    public static final RatioUnit LEAGUE = new RatioUnit(3, MILE);

    public static final IntervalUnit CELSIUS = new IntervalUnit();
    public static final IntervalUnit FAHRENHEIT = new IntervalUnit(5/9.0, 32, CELSIUS);

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

    double convertedAmount(double otherAmount, Unit other) {
        return baseUnitAmount(otherAmount, other) / this.baseUnitRatio + this.offset;
    }

    double baseUnitAmount(double otherAmount, Unit other) {
        if (!this.isCompatible(other)) throw new IllegalArgumentException("Incompatible Units");
        return (otherAmount - other.offset) * other.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hashCode((amount - offset) * baseUnitRatio);
    }

    boolean isCompatible(Unit other) {
        return this.baseUnit == other.baseUnit;
    }

    public static class RatioUnit extends Unit {

        private RatioUnit() { super(); }

        private RatioUnit(double relativeRatio, Unit relativeUnit) {
            super(relativeRatio, 0.0, relativeUnit);
        }

        public RatioQuantity s(double amount) {
            return new RatioQuantity(amount, this);
        }

        public RatioQuantity es(double amount) {
            return s(amount);
        }
    }

    public static class IntervalUnit extends Unit {

        private IntervalUnit() { super(); }

        private IntervalUnit(double relativeRatio, double offset, Unit relativeUnit) {
            super(relativeRatio, offset, relativeUnit);
        }

        public IntervalQuantity s(double amount) {
            return new IntervalQuantity(amount, this);
        }

        public IntervalQuantity es(double amount) {
            return s(amount);
        }
    }
}
