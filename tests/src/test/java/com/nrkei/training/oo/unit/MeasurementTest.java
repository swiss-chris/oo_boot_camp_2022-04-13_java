/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit;

import com.nrkei.training.oo.measure.Measurement;
import com.nrkei.training.oo.measure.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Ensures Measurement works correctly
public class MeasurementTest {

    @Test
    void equals() {
        final Measurement oneTeaspoon1 = new Measurement(1, Unit.TEASPOON);
        final Measurement oneTeaspoon2 = new Measurement(1, Unit.TEASPOON);

        assertEquals(oneTeaspoon1, oneTeaspoon1);
        assertEquals(oneTeaspoon1, oneTeaspoon2);
        assertEquals(oneTeaspoon2, oneTeaspoon1);
        assertEquals(new Measurement(1, Unit.TABLESPOON), new Measurement(3, Unit.TEASPOON));
        assertEquals(new Measurement(1, Unit.OUNCE), new Measurement(6, Unit.TEASPOON));
        assertEquals(new Measurement(1.5, Unit.OUNCE), new Measurement(9, Unit.TEASPOON));
        assertNotEquals(new Measurement(1, Unit.TABLESPOON), new Measurement(1, Unit.TEASPOON));
    }


}
