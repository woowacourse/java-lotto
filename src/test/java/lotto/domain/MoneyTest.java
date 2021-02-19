package lotto.domain;

import lotto.exception.IllegalMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "a", "500"})
    @DisplayName("1000 이상의 정수인지 검증")
    void isValidMoney(String input) {
        assertThatThrownBy(() -> {
            Money money = new Money(input);
        }).isInstanceOf(IllegalMoneyException.class);
    }

    @Test
    @DisplayName("돈의 합 계산")
    void plus() {
        Money money1 = Money.ZERO;
        Money money2 = new Money(10000);
        assertThat(money1.plus(money2).getValue()).isEqualTo(10000);
    }

    @Test
    @DisplayName("돈(0원)과 count의 곱셈 계산")
    void multiple1() {
        Money money = Money.ZERO;
        assertThat(money.multiple(10).getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("돈과 count의 곱셈 계산")
    void multiple2() {
        Money money = new Money(10000);
        assertThat(money.multiple(10).getValue()).isEqualTo(100000);
    }

    @Test
    @DisplayName("수익률 계산")
    void getProfitRate() {
        Money money = new Money("10000");
        assertThat(money.getProfitRate(10)).isEqualTo(1.0);
    }
}
