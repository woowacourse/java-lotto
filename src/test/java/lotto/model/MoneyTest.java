package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @DisplayName("음수가 들어오면 돈을 생성할 수 없다.")
    @Test
    void createMoneyWithNegative() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격을 알려주면 몇 개 구매할 수 있는지 계산한다.")
    @Test
    void calculateBuyingCountWithUnitPrice() {
        Money money = new Money(1_000);

        assertThat(money.calculateBuyingCount(1_000)).isEqualTo(1);
    }

    @DisplayName("가격을 알려주면 구매 후 잔돈을 계산한다.")
    @Test
    void calculateChangeWithUnitPrice() {
        Money money = new Money(1_100);

        assertThat(money.calculateChange(1_000)).isEqualTo(100);
    }

    @DisplayName("총 수익금을 알려주면 수익률을 계산한다.")
    @Test
    void calculateReturnRatio() {
        Money money = new Money(14_000);

        assertThat(money.calculateReturnRatio(5_000L)).isEqualTo(0.35);
    }

    @DisplayName("다른 금액을 알려주면 부족한지 알려준다.")
    @CsvSource(value = {"999,true", "1_000,false"})
    @ParameterizedTest
    void lessThanOtherAmount(int amount, boolean expected) {
        Money money = new Money(amount);

        assertThat(money.isLessThan(1_000)).isEqualTo(expected);
    }

}
