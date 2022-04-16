/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.nrkei.training.oo.quantity.DistanceUnit.FOOT;
import static com.nrkei.training.oo.quantity.DistanceUnit.INCH;
import static com.nrkei.training.oo.quantity.DistanceUnit.MILE;
import static com.nrkei.training.oo.quantity.DistanceUnit.YARD;
import static com.nrkei.training.oo.quantity.TemperatureUnit.CELSIUS;
import static com.nrkei.training.oo.quantity.TemperatureUnit.FAHRENHEIT;
import static com.nrkei.training.oo.quantity.TemperatureUnit.KELVIN;
import static com.nrkei.training.oo.quantity.VolumeUnit.CUP;
import static com.nrkei.training.oo.quantity.VolumeUnit.GALLON;
import static com.nrkei.training.oo.quantity.VolumeUnit.OUNCE;
import static com.nrkei.training.oo.quantity.VolumeUnit.PINT;
import static com.nrkei.training.oo.quantity.VolumeUnit.QUART;
import static com.nrkei.training.oo.quantity.VolumeUnit.TABLESPOON;
import static com.nrkei.training.oo.quantity.VolumeUnit.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Ensures Quantity operates correctly
public class QuantityTest {

    @Test void equalityOfLikeUnits() {
        assertEquals(TABLESPOON.s(4), TABLESPOON.s(4));
        assertNotEquals(TABLESPOON.s(4), TABLESPOON.s(6));
        assertNotEquals(TABLESPOON.s(4), new Object());
        assertNotEquals(TABLESPOON.s(4), null);
    }

    @Test void equalityOfDifferentUnits() {
        assertNotEquals(TABLESPOON.s(4), TEASPOON.s(4));
        assertEquals(CUP.s(1/4.0), TABLESPOON.s(4));
        assertEquals(GALLON.s(1), TEASPOON.s(768));
        assertNotEquals(TABLESPOON.s(4), TEASPOON.s(4));
        assertEquals(MILE.s(1), INCH.es(12 * 5280));
    }

    @Test void setOperations() {
        assertTrue(new HashSet<>(Collections.singletonList(TABLESPOON.s(4))).contains(TABLESPOON.s(4)));
        assertTrue(new HashSet<>(Collections.singletonList(TABLESPOON.s(4))).contains(OUNCE.s(2)));
        assertEquals(1, new HashSet<>(Arrays.asList(TABLESPOON.s(4), TABLESPOON.s(4))).size());
        assertEquals(1, new HashSet<>(Arrays.asList(TABLESPOON.s(4), OUNCE.s(2))).size());
    }

    @Test void hash() {
        assertEquals(TABLESPOON.s(4).hashCode(), TABLESPOON.s(4).hashCode());
        assertEquals(TABLESPOON.s(4).hashCode(), CUP.s(1/4.0).hashCode());
        assertEquals(CELSIUS.es(10).hashCode(), FAHRENHEIT.s(50).hashCode());
    }

    @Test void arithmetic() {
        assertEquals(QUART.s(0.5), TABLESPOON.s(6).plus(OUNCE.s(13)));
        assertEquals(TABLESPOON.s(-6), TABLESPOON.s(6).negate());
        assertEquals(PINT.s(-0.5), TABLESPOON.s(10).minus(OUNCE.s(13)));
        assertEquals(FOOT.s(-4), INCH.es(24).minus(YARD.s(2)));
    }

    @Test void crossMetricType() {
        assertNotEquals(INCH.es(1), TEASPOON.s(1));
        assertNotEquals(OUNCE.s(4), FOOT.s(2));
    }

    @Test void temperature() {
        assertTemperatureSymmetry(-273.15, -459.67, 0);
        assertTemperatureSymmetry(0, 32, 273.15);
        assertTemperatureSymmetry(10, 50);
        assertTemperatureSymmetry(100, 212);
        assertTemperatureSymmetry(-40, -40);
    }

    @Test void plusTemperature() {
        assertEquals(CELSIUS.es(1), CELSIUS.es(0).plus(CELSIUS.es(1)));
        assertEquals(CELSIUS.es(5/9d), CELSIUS.es(0).plus(FAHRENHEIT.es(1)));
        assertEquals(FAHRENHEIT.es(1+9/5d), FAHRENHEIT.es(1).plus(CELSIUS.es(1)));
    }

    private void assertTemperatureSymmetry(double celsius, double fahrenheit, double kelvin) {
        var c = CELSIUS.es(celsius);
        var f = FAHRENHEIT.s(fahrenheit);
        var k = KELVIN.s(kelvin);
        assertEquals(c, f);
        assertEquals(c, k);
        assertEquals(f, c);
        assertEquals(f, k);
        assertEquals(k, c);
        assertEquals(k, f);
    }

    private void assertTemperatureSymmetry(double celsius, double fahrenheit) {
        var c = CELSIUS.es(celsius);
        var f = FAHRENHEIT.s(fahrenheit);
        assertEquals(c, f);
        assertEquals(f, c);
    }
}
