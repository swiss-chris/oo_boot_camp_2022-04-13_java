/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity.units;

import com.nrkei.training.oo.quantity.IntervalUnit;

public class VolumeUnit extends IntervalUnit {
    public static final VolumeUnit TEASPOON = new VolumeUnit();
    public static final VolumeUnit TABLESPOON = new VolumeUnit(3, TEASPOON);
    public static final VolumeUnit OUNCE = new VolumeUnit(2, TABLESPOON);
    public static final VolumeUnit CUP = new VolumeUnit(8, OUNCE);
    public static final VolumeUnit PINT = new VolumeUnit(2, CUP);
    public static final VolumeUnit QUART = new VolumeUnit(2, PINT);
    public static final VolumeUnit GALLON = new VolumeUnit(4, QUART);

    private VolumeUnit() {
        super();
    }

    private VolumeUnit(double relativeRatio, VolumeUnit relativeUnit) {
        super(relativeRatio, relativeUnit);
    }
}
