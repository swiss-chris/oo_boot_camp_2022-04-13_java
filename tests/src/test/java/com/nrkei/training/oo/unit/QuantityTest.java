/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.quantity.IntervalUnit;
import com.nrkei.training.oo.quantity.RatioUnit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.nrkei.training.oo.quantity.RatioUnit.CELSIUS;
import static com.nrkei.training.oo.quantity.RatioUnit.CUP;
import static com.nrkei.training.oo.quantity.RatioUnit.FOOT;
import static com.nrkei.training.oo.quantity.RatioUnit.GALLON;
import static com.nrkei.training.oo.quantity.RatioUnit.INCH;
import static com.nrkei.training.oo.quantity.RatioUnit.MILE;
import static com.nrkei.training.oo.quantity.RatioUnit.OUNCE;
import static com.nrkei.training.oo.quantity.RatioUnit.PINT;
import static com.nrkei.training.oo.quantity.RatioUnit.QUART;
import static com.nrkei.training.oo.quantity.RatioUnit.TABLESPOON;
import static com.nrkei.training.oo.quantity.RatioUnit.TEASPOON;
import static com.nrkei.training.oo.quantity.RatioUnit.YARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Ensures RatioQuantity operates correctly
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
        assertEquals(RatioUnit.CELSIUS.es(10).hashCode(), RatioUnit.FAHRENHEIT.s(18).hashCode());
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

    @Test void mixedUnitArithmetic() {
        assertThrows(IllegalArgumentException.class, () ->
                YARD.s(3).minus(TABLESPOON.s(4)));
    }

    @Test void temperature_Ratio() {
        assertTemperatureSymmetry_Ratio(0, 0);
        assertTemperatureSymmetry_Ratio(10, 18);
    }

    private void assertTemperatureSymmetry_Ratio(double celsius, double fahrenheit) {
        var c = RatioUnit.CELSIUS.es(celsius);
        var f = RatioUnit.FAHRENHEIT.s(fahrenheit);
        assertEquals(c, f);
        assertEquals(f, c);
    }

    @Test void temperature_Interval() {
        assertTemperatureSymmetry_Interval(0, 32);
        assertTemperatureSymmetry_Interval(10, 50);
        assertTemperatureSymmetry_Interval(100, 212);
        assertTemperatureSymmetry_Interval(-40, -40);
    }

    private void assertTemperatureSymmetry_Interval(double celsius, double fahrenheit) {
        var c = IntervalUnit.CELSIUS.es(celsius);
        var f = IntervalUnit.FAHRENHEIT.s(fahrenheit);
        assertEquals(c, f);
        assertEquals(f, c);
    }
}
