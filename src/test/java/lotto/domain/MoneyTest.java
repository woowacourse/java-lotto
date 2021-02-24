package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @DisplayName("숫자를 인자로 받아 금액을 생성하는지 확인")
    @Test
    void 금액_객체_생성_테스트() {
        int expectedMoneyAmount = 1000;
        Money money = new Money(expectedMoneyAmount);

        assertThat(money.getMoney()).isEqualTo(expectedMoneyAmount);
    }

    @DisplayName("금액이 0보다 작으면 예외처리")
    @Test
    void 금액이_0보다_작을때() {
        int testAmount = -100;
        assertThatThrownBy(() -> new Money(testAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액 빼기 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1500,1000", "3500,3000", "10000,1500", "2300,800", "4321,1234"})
    void 금액_빼기_테스트(int originMoneyAmount, int minusMoneyAmount) {
        Money originMoney = new Money(originMoneyAmount);
        Money minusMoney = new Money(minusMoneyAmount);
        assertThat(originMoney.minus(minusMoney).getMoney()).isEqualTo(originMoneyAmount - minusMoneyAmount);
    }

    @DisplayName("금액 나누기 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1500,2,750", "10000,4,2500"})
    void 금액_나누기_테스트(int originMoneyAmount, int times, int expectedMoney) {
        Money originMoney = new Money(originMoneyAmount);
        Money divideMoney = new Money(times);

        assertThat(originMoney.divide(divideMoney)).isEqualTo(expectedMoney);
    }
}