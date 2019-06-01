package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    void 로또_최소_가격보다_작은_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(900);
        });
    }

    @Test
    void 정확히_나누어_떨어지는_금액_입력() {
        Money money = new Money(14000);
        assertThat(14).isEqualTo(money.getNumberOfLotto());
    }

    @Test
    void 정확히_나누어_떨어지지_않는_금액_입력() {
        Money money = new Money(14200);
        assertThat(14).isEqualTo(money.getNumberOfLotto());
    }
}
