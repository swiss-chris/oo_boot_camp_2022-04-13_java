/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity.units;

import com.nrkei.training.oo.quantity.IntervalUnit;

public class TemperatureUnit extends IntervalUnit {

    public static final TemperatureUnit CELSIUS = new TemperatureUnit();
    public static final TemperatureUnit FAHRENHEIT = new TemperatureUnit(5/9.0, 32, CELSIUS);
    public static final TemperatureUnit KELVIN = new TemperatureUnit(1, 273.15, CELSIUS);

    private TemperatureUnit() {
        super();
    }

    private TemperatureUnit(double relativeRatio, double offset, TemperatureUnit relativeUnit) {
        super(relativeRatio, relativeUnit, offset);
    }
}
