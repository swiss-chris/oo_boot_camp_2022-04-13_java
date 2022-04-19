package com.nrkei.training.oo.quantity;

public class IntervalUnit {
    public static final IntervalUnit CELSIUS = new IntervalUnit(RatioUnit.CELSIUS, 0);
    public static final IntervalUnit FAHRENHEIT = new IntervalUnit(RatioUnit.FAHRENHEIT, 0);

    final RatioUnit ratioUnit;
    private final double offset;

    private IntervalUnit(RatioUnit ratioUnit, double offset) {
        this.ratioUnit = ratioUnit;
        this.offset = offset;
    }

    public IntervalQuantity s(double amount) {
        return new IntervalQuantity(amount, this);
    }

    public IntervalQuantity es(double amount) {
        return s(amount);
    }

    double convertedAmount(double otherAmount, IntervalUnit other) {
        if (!this.isCompatible(other)) throw new IllegalArgumentException("Incompatible Units");
        return (otherAmount - other.offset) * other.ratioUnit.baseUnitRatio / this.ratioUnit.baseUnitRatio + this.offset;
    }

    boolean isCompatible(IntervalUnit other) {
        return this.ratioUnit.baseUnit == other.ratioUnit.baseUnit;
    }
}
