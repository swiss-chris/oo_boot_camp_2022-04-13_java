package com.nrkei.training.oo.measure;

public enum Unit {

    // Imperial Volume
    TEASPOON(),
    TABLESPOON(3, TEASPOON),
    OUNCE(2, TABLESPOON),
    CUP(8, OUNCE),
    PINT(2, CUP),
    QUART(2, PINT),
    GALLON(4, PINT),

    // Imperial Distance
    INCH(),
    FOOT(12, INCH),
    YARD(3, FOOT),
    FATHOM(6, FOOT),
    CHAIN(22, YARD),
    LINK(1d/100, CHAIN),
    FURLONG(10, CHAIN),
    MILE(8, FURLONG),
    LEAGUE(3, MILE);

    private final double baseUnitRatio;

    Unit() {
        baseUnitRatio = 1;
    }

    Unit(double relativeRatio, Unit relativeUnit) {
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }

    double convertedAmount(Unit other) {
        return this.baseUnitRatio / other.baseUnitRatio;
    }
}
