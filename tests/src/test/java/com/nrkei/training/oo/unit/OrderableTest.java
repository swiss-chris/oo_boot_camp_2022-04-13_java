/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.Orderable;
import com.nrkei.training.oo.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

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
}
