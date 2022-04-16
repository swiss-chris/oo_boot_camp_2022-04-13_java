/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity.units;

import com.nrkei.training.oo.quantity.IntervalUnit;

public class DistanceUnit extends IntervalUnit {
    public static final DistanceUnit INCH = new DistanceUnit();
    public static final DistanceUnit FOOT = new DistanceUnit(12, INCH);
    public static final DistanceUnit YARD = new DistanceUnit(3, FOOT);
    public static final DistanceUnit CHAIN = new DistanceUnit(22, YARD);
    public static final DistanceUnit FURLONG = new DistanceUnit(10, CHAIN);
    public static final DistanceUnit MILE = new DistanceUnit(8, FURLONG);

    private DistanceUnit() {
        super();
    }

    private DistanceUnit(double relativeRatio, DistanceUnit relativeUnit) {
        super(relativeRatio, relativeUnit);
    }
}
