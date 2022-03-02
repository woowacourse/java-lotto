package lotto.domain.vo;

import static lotto.domain.vo.ManualTicketNumber.NOT_BUY_MANUAL_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualTicketNumberTest {

    @Test
    @DisplayName("수동 구매가 가능하면 정상 입력된다")
    void can_buy_manual_ticket() {
        // given & when & then
        Assertions.assertDoesNotThrow(() -> {
            PurchaseAmount purchaseAmount = new PurchaseAmount("2000");
            new ManualTicketNumber("1", purchaseAmount);
        });
    }

    @Test
    @DisplayName("수동 구매가 구매 가능 장수를 넘기면 예외가 발생한다")
    void can_not_buy_manual_ticket() {
        // given & when & then
        assertThatThrownBy(
                () -> new ManualTicketNumber("3", new PurchaseAmount("2000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_BUY_MANUAL_MESSAGE);
    }
}
