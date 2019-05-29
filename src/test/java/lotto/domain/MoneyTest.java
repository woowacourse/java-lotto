package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 돈이_부족한_경우() {
        assertThrows(IllegalArgumentException.class, () -> new Money(500));
    }

    @Test
    void 최대_구매_가능_금액을_넘어간_경우() {
        assertThrows(IllegalArgumentException.class, () -> new Money(1_000_000_001));
    }
}
