package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchasementTest {
    @Test
    void 구입_금액이_1000원_미만인_경우_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> new Purchasement(999));
    }
}
