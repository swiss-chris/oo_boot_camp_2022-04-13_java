package com.nrkei.training.oo.measure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    public void convertedAmount() {
        Assertions.assertEquals(3, Unit.TABLESPOON.convertedAmount(Unit.TEASPOON));
        Assertions.assertEquals(1d/100, Unit.LINK.convertedAmount(Unit.CHAIN));
        Assertions.assertEquals(100, Unit.CHAIN.convertedAmount(Unit.LINK));
    }
}
