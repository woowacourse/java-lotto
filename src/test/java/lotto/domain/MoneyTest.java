package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    void 금액_음수() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(-1);
        });
    }

    @Test
    void 티켓수_반환() {
        Money money = new Money(3600);
        assertThat(money.ticketAmount()).isEqualTo(3);
    }
}