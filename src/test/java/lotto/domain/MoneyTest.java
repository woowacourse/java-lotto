package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("천원 단위의 돈을 생성한다.")
    void createMoney() {
        // given
        Money money = new Money(1000);
        // then
        assertThat(money.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("천원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void throwsExceptionNotDividableWithThousand() {
        // given
        int amount = 1500;
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Money(amount));
    }

    @Test
    @DisplayName("음수의 금액을 생성할 수 없다.")
    void throwsExceptionWithNegativeAmount() {
        // given
        int amount = -1000;
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Money(amount));
    }

    @Test
    @DisplayName("정상적으로 돈으로 나누는지 확인")
    void divideByAmount() {
        // given
        int amount = 3000;
        Money money = new Money(amount);
        // then
        assertThat(money.divideByAmount(4500L)).isEqualTo(1.50);
    }
}
