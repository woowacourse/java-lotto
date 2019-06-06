package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseAmountTest {
    @Test
    void 구입_금액이_1000원_미만인_경우_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseAmount.of(999));
    }

    @Test
    void 구입_금액이_1000원의_배수가_아닌_경우_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseAmount.of(1001));
    }
}
