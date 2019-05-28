package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    void 로또_최소_가격보다_작은_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(900);
        });
    }
}
