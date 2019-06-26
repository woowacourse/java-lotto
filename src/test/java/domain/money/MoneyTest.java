package domain.money;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void Money_곱셈_테스트() {
        assertThat(Money.amountOf(5).multiply(2)).isEqualTo(Money.amountOf(10));
    }
}