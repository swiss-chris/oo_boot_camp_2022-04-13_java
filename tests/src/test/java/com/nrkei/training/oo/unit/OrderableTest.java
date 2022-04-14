/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.Orderable;
import com.nrkei.training.oo.probability.Chance;
import com.nrkei.training.oo.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

import static com.nrkei.training.oo.quantity.Unit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Ensures Orderable types behave correctly
class OrderableTest {

    @Test void rectangleWithLargestArea() {
        assertEquals(24, (Orderable.bestOrNull(
                new Rectangle(2, 3), new Rectangle(4, 6), Rectangle.square(3)))
                .area());
        assertNull(Orderable.bestOrNull());
    }

    @Test void leastLikelyChance() {
        assertEquals(new Chance(0.25), Orderable.bestOrNull(
                new Chance(0.5), new Chance(0.25), new Chance(0.75)
        ));
    }

    @Test void testMaxQuantity() {
        assertEquals(QUART.s(2), Orderable.bestOrNull(
                GALLON.s(0.2), OUNCE.s(24), GALLON.s(0.5), CUP.s(7)
        ));
        assertEquals(CELSIUS.s(100), Orderable.bestOrNull(
                FAHRENHEIT.s(212), CELSIUS.s(0), FAHRENHEIT.s(50), CELSIUS.s(-40)
        ));
    }
}
