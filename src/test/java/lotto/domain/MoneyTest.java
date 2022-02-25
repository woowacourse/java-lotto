package lotto.domain;

import static lotto.domain.Money.NOT_ENOUGH_MONEY;
import static lotto.domain.Money.NOT_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("로또는 최소 천원이 있어야 구매할 수 있다.")
    void underLottoTicketPrice() {
        // given
        String notEnoughMoney = "999";

        // then
        assertThatThrownBy(() -> new Money(notEnoughMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ENOUGH_MONEY);
    }

    @Test
    @DisplayName("구입 금액은 숫자여야 한다.")
    void notNumber() {
        // given
        String notNumberString = "abc";

        // then
        assertThatThrownBy(() -> new Money(notNumberString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMBER);
    }
}
