package com.nrkei.training.oo.measure;

import java.util.Objects;

// understands a quantity of units
public class Measurement {

    private final double amount;
    private final Unit unit;

    @Override
    public String toString() {
        return "Measurement(" + this.amount * this.unit.convertedAmount(Unit.TEASPOON) + ")";
    }

    public Measurement(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return this.amount == that.amount * that.unit.convertedAmount(this.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit); // TODO
    }
}


