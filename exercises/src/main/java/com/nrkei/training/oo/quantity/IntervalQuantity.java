package com.nrkei.training.oo.quantity;

public class IntervalQuantity {
    private final double amount;
    private final IntervalUnit intervalUnit;

    IntervalQuantity(double amount, IntervalUnit intervalUnit) {
        this.amount = amount;
        this.intervalUnit = intervalUnit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof IntervalQuantity && this.equals((IntervalQuantity) other);
    }

    private boolean equals(IntervalQuantity other) {
        return this.isCompatible(other) && this.amount == convertedAmount(other);
    }

    private boolean isCompatible(IntervalQuantity other) {
        return this.intervalUnit.isCompatible(other.intervalUnit);
    }

    @Override
    public String toString() {
        return "IntervalQuantity{" +
                "amount=" + this.intervalUnit.ratioUnit.baseUnit.convertedAmount(this.amount, this.intervalUnit.ratioUnit)+
                '}';
    }

    private double convertedAmount(IntervalQuantity other) {
        return this.intervalUnit.convertedAmount(other.amount, other.intervalUnit);
    }
}
