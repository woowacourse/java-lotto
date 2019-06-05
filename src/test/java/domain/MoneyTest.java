package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void lessThan_() {
        int price = 100;
        Money lesser = Money.from(price - 1);
        Money greater = Money.from(price);

        assertThat(lesser.lessThan(greater)).isTrue();
    }

    @Test
    void minus_() {
        int price1 = 100;
        int price2 = 30;
        Money money1 = Money.from(price1);
        Money money2 = Money.from(price2);

        assertThat(money1.minus(money2)).isEqualTo(Money.from(price1 - price2));
    }

    @Test
    void times() {
        int price = 3;
        int n = 100;

        assertThat(Money.from(price).times(100)).isEqualTo(Money.from(n * price));
    }
}