package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("로또 상품금 더하기 테스트")
    void add() {
        Money money = Money.ZERO;
        Money actualMoney = money.plus(new Money(2_000_000_000));
        assertThat(actualMoney).isEqualTo(new Money(2_000_000_000));
    }

    @Test
    @DisplayName("로또 상품금 곱하기 테스트")
    void multiply() {
        Money money = new Money(2);
        Money actualMoney = money.multiply(4);
        assertThat(actualMoney).isEqualTo(new Money(8));
    }

    @Test
    void divide() {
        Money money = new Money(100);
        BigDecimal shareValue = money.divide(new Money(4));
        assertThat(shareValue).isEqualTo(new BigDecimal("25"));
    }

}
