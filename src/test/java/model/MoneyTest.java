package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("로또 상품금 더하기 테스트")
    void addPrizeTest() {
        Money prize = Money.ZERO;
        Money actualPrize = prize.add(new Money(2_000_000_000));
        assertThat(actualPrize).isEqualTo(new Money(2_000_000_000));
    }

    @Test
    @DisplayName("로또 상품금 곱하기 테스트")
    void multiplyPrize() {
        Money prize = new Money(2);
        Money actualPrize = prize.multiply(4);
        assertThat(actualPrize).isEqualTo(new Money(8));
    }

}
