package lotto.domain.machine;


import lotto.domain.machine.exeption.InvalidMinimumMoneyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {
    @Test
    public void 로또_가격_미만_입금_에러() {
        assertThrows(InvalidMinimumMoneyException.class, () -> {
            Money.of(900);
        });
    }

    @Test
    public void 돈에_맞는_로또_갯수_반환() {
        assertThat(Money.of(5500).ticketQuantity()).isEqualTo(5);
    }

    @Test
    public void 잔돈_반환() {
        assertThat(Money.of(5500).getRest()).isEqualTo(500);
    }


}