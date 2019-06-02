package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 천원_단위가_아닐때() {
        assertThrows(IllegalArgumentException.class, () -> {
            Money money = new Money(19999);
        });
    }

    @Test
    void 로또_갯수_주기() {
        Money money = new Money(4000);
        assertThat(money.getRound()).isEqualTo(4);
    }
}
