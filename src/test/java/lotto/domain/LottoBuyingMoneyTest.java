package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoBuyingMoneyTest {
    @Test
    void validateMoneyTest() {
        assertThrows(InvalidMoneyException.class, () -> new LottoBuyingMoney(-1));
    }

    @Test
    void isMultipleOfTest() {
        assertThat((new LottoBuyingMoney(1000)).isMultipleOf(1000)).isTrue();
        assertThat((new LottoBuyingMoney(1000)).isMultipleOf(300)).isFalse();
    }

    @Test
    void divideTest() {
        assertThat((new LottoBuyingMoney(5000)).divideBy(1000)).isEqualTo(5);
    }
}
