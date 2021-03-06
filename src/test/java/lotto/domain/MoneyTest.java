package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.Money.MONEY_RANGE_ERROR;
import static lotto.domain.Money.MONEY_UNIT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("구입금액이 숫자인지 확인")
    void validateMoneyType() {
        final String falseMoney = "로또값";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Money.MONEY_INT_ERROR);
    }

    @Test
    @DisplayName("구입금액이 1000이상인지 확인")
    void validateMoneyRange() {
        final String falseMoney = "500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_RANGE_ERROR);
    }

    @Test
    @DisplayName("구입금액이 1000단위인지 확인")
    void validateMoneyUnit() {
        final String falseMoney = "1500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_UNIT_ERROR);
    }

    @Test
    @DisplayName("자동로또 개수가 잘 계산되는지 확인")
    void getLeftCount() {
        final Money money = new Money("14000");
        assertThat(money.getLeftCount(3)).isEqualTo(11);
    }
}
