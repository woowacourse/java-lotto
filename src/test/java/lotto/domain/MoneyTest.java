package lotto.domain;

import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @DisplayName("Money 객체 생성")
    @Test
    void of() {
        assertThat(Money.of(100)).isNotNull();
    }

    @DisplayName("최소값이상의 금액으로 Money 객체 생성")
    @Test
    void createPurchaseMoney() {
        assertThat(Money.createPurchaseMoney(1000)).isNotNull();
    }

    @DisplayName("최소값보다 적은 금액일 경우 예외 발생")
    @Test
    void createPurchaseMoneyUnderMinimum() {
        Assertions.assertThatThrownBy(() -> {
            Money.createPurchaseMoney(999);
        }).isInstanceOf(MoneyException.class)
                .hasMessage("구매금액은 1000원 이상이어야 합니다.");
    }

    @DisplayName("Money를 다른 Money와 더함")
    @Test
    void plus() {
        Money money = Money.of(100);
        Money plusAmount = Money.of(900);

        Money actual = money.plus(plusAmount);
        Money expected = Money.of(1000);

        assertThat(actual).isEqualTo(expected);

    }

    @DisplayName("Money와 다른 Money의 몫을 계산")
    @Test
    void calculateQuotient() {
        Money money = Money.of(14500);
        Money dividingMoney = Money.of(1000);

        int actual = money.calculateQuotient(dividingMoney);
        int expected = 14;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Money를 다른 Money로 나눈 백분율을 계산")
    @Test
    void calculatePercentage() {
        Money money = Money.of(5000);
        Money dividingMoney = Money.of(14000);

        double actual = money.calculatePercentage(dividingMoney);
        double expected = (double) 5000 / 14000 * 100;

        assertThat(actual).isEqualTo(expected);
    }
}