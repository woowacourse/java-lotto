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
    void 구매할_수_있는_로또_개수_계산() {
        Money money = new Money(14000);
        assertThat(14).isEqualTo(money.getNumberOfLotto());
    }

    @Test
    void 구매할_수_있는_로또_개수_계산2() {
        Money money = new Money(14200);
        assertThat(14).isEqualTo(money.getNumberOfLotto());
    }
}
