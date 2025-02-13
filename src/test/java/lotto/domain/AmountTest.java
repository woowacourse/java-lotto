package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTest {
    @Test
    @DisplayName("정상적인 값을 입력헀을 때 정상적으로 생성된다.")
    void testAmount() {
        Amount amount = new Amount(1000);
        assertThat(amount.getAmount() == 1).isTrue();
    }

    @Test
    @DisplayName("Amount가 0개일 때 예외가 발생한다.")
    void testAmountZero() {
        assertThatThrownBy(() -> new Amount(100))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000원으로 나누어 떨어지지 않을 때 예외가 발생한다.")
    void testAmountNotDivided() {
        assertThatThrownBy(() -> new Amount(1500))
            .isInstanceOf(IllegalArgumentException.class);
    }
}