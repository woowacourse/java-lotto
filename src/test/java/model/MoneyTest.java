package model;

import static model.Money.NOT_ENOUGH_MONEY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("로또는 최소 천원이 있어야 구매할 수 있다.")
    void underLottoTicketPrice() {
        // given
        int notEnoughAmount = 999;

        // then
        assertThatThrownBy(() -> new Money(notEnoughAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ENOUGH_MONEY);
    }
}
