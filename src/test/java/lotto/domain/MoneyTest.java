package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static lotto.domain.Money.MONEY_RANGE_ERROR;
import static lotto.domain.Money.MONEY_UNIT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("구입금액이_숫자인지_확인")
    void validateMoneyType() {
        String falseMoney = "로또값";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Money.MONEY_INT_ERROR);
    }

    @Test
    @DisplayName("구입금액이 1000이상인지 확인")
    void validateMoneyRange() {
        String falseMoney = "500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_RANGE_ERROR);
    }

    @Test
    @DisplayName("구입금액이 1000단위인지 확인")
    void validateMoneyUnit() {
        String falseMoney = "1500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_UNIT_ERROR);
    }

    @Test
    @DisplayName("당첨금액과 수익률 계산")
    void calculateEarnings() {
        Map<Rank, Integer> sampleCount = new HashMap<>();
        sampleCount.put(Rank.FIFTH, 1);
        Money money = new Money("14000");

        Money.findEarning(sampleCount);
        assertThat(Money.getEarning()).isEqualTo(5000);

        String earningRate = Money.findEarningRate();
        assertThat(earningRate).isEqualTo("0.36");
    }
}
