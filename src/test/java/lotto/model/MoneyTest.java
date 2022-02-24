package lotto.model;

import lotto.model.money.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("천원 단위의 돈을 생성한다.")
    public void createMoney() {
        // given
        Money money = Money.of(1000);
        // then
        Assertions.assertThat(money).isNotNull();
    }

    @Test
    @DisplayName("천원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void throwsExceptionNotDividableWithThousand() {
        // given
        int amount = 1500;
        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Money.of(amount));
    }

    @Test
    @DisplayName("음수의 금액을 생성할 수 없다.")
    void throwsExceptionWithNegativeAmount() {
        // given
        int amount = -1000;
        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Money.of(amount));
    }
}
