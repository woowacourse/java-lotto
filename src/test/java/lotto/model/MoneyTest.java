package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import lotto.model.exception.NotEnoughMoneyException;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("금액 더하기 테스트")
    void add() {
        Money money = Money.ZERO;
        Money actualMoney = money.plus(new Money(2_000_000_000));
        assertThat(actualMoney).isEqualTo(new Money(2_000_000_000));
    }

    @Test
    @DisplayName("금액 곱하기 테스트")
    void multiply() {
        Money money = new Money(2);
        Money actualMoney = money.multiply(4);
        assertThat(actualMoney).isEqualTo(new Money(8));
    }

    @Test
    @DisplayName("금액 나누기 테스트")
    void divide() {
        Money money = new Money(100);
        BigDecimal shareValue = money.divide(new Money(4));
        assertThat(shareValue.doubleValue()).isCloseTo(new BigDecimal("25").doubleValue(),
            Percentage.withPercentage(0.01));
    }

    @Test
    @DisplayName("금액 빼기 테스트")
    void subtract() {
        Money money = new Money(1000);
        Money actual = money.subtract(new Money(500));
        assertThat(actual).isEqualTo(new Money(500));
    }

    @Test
    @DisplayName("금액 빼기 결과가 마이너스 인 경우 예외 발생")
    void invalidSubtract() {
        Money money = new Money(1000);
        assertThatThrownBy(() -> money.subtract(new Money(2000)))
            .isInstanceOf(NotEnoughMoneyException.class);
    }

}
