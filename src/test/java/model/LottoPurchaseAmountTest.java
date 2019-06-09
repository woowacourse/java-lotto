package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoPurchaseAmountTest {
    @Test
    void validationTest() {
        assertThatThrownBy(() -> new LottoPurchaseAmount(new Money(10000), 12));
    }
}