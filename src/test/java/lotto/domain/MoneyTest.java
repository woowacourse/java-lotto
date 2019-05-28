package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void validateMoneyBiggerThanZero() {
        assertThrows(InvalidMoneyException.class, () -> new Money(0));
    }

    @Test
    void isMultipleOfTest() {
        assertThat((new Money(1000)).isMultipleOf(new Money(1000))).isTrue();
        assertThat((new Money(1000)).isMultipleOf(new Money(300))).isFalse();
    }

    @Test
    void divideTest() {
        assertThat((new Money(5000)).divideBy(new Money(1000))).isEqualTo(5);
    }
}
