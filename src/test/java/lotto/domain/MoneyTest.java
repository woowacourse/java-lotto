package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @DisplayName("올바른 입력값 확인")
    @Test
    void is_number() {
        Money money = new Money("1000");

        assertThat(money).isEqualTo(new Money("1000"));
    }

    @DisplayName("숫자인지 확인 ")
    @Test
    void is_not_number() {
        assertThatThrownBy(() -> {
            new Money("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000원 이하 인지 확인 ")
    @Test
    void is_less_1000() {
        assertThatThrownBy(() -> {
            new Money("999");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액 따라 로또 갯수 산출 하는지")
    @Test
    void calculateNumberOfLotto() {
        Money money = new Money("13500");
        int numberOfLotto = money.calculateNumberOfLotto();

        assertThat(numberOfLotto).isEqualTo(13);
    }

    @DisplayName("수익률 계산")
    @Test
    void calculateProfitRate() {
        Money money = new Money("1000");
        int profit = 10000;

        assertThat(money.calculateProfitRate(profit)).isEqualTo(10);
    }
}