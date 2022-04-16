/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity;

public class TemperatureUnit extends Unit {

    public static final TemperatureUnit CELSIUS = new TemperatureUnit();
    public static final TemperatureUnit FAHRENHEIT = new TemperatureUnit(5/9.0, 32, CELSIUS);

    private TemperatureUnit() {
        super();
    }

    private TemperatureUnit(double relativeRatio, double offset, TemperatureUnit relativeUnit) {
        super(relativeRatio, offset, relativeUnit);
    }
}
