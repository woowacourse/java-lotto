package lotto.domain.vo;

import static lotto.domain.vo.PurchaseAmount.NOT_ENOUGH_MONEY;
import static lotto.domain.vo.PurchaseAmount.NOT_NUMBER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.vo.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseAmountTest {

    @Test
    @DisplayName("로또는 최소 천원이 있어야 구매할 수 있다.")
    void underLottoTicketPrice() {
        // given
        String notEnoughMoney = "999";

        // then
        assertThatThrownBy(() -> new PurchaseAmount(notEnoughMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ENOUGH_MONEY);
    }

    @Test
    @DisplayName("구입 금액은 숫자여야 한다.")
    void notNumber() {
        // given
        String notNumberString = "abc";

        // then
        assertThatThrownBy(() -> new PurchaseAmount(notNumberString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMBER_MESSAGE);
    }
}
