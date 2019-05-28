package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void validateMoneyBiggerThanZero() {
        assertThrows(InvalidMoney.class, () -> new Money(0));
    }
}
