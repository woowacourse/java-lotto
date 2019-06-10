package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {
    @Test
    void lowerThanZeroTest() {
        assertThrows(InvalidMoneyException.class, () -> new Money(-1));
    }

    @Test
    void add() {
        assertThat((new Money(1000)).add(new Money(1000))).isEqualTo(new Money(2000));
    }
}