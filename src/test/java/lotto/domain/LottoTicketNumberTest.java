package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketNumberTest {
    @Test
    @DisplayName("구매할 수 있는 수량보다 수동 티켓을 입력했을 경우 테스트")
    void out_of_manual_ticket_number() {
        PurchaseAmount purchaseAmount = new PurchaseAmount("5000");
        Assertions.assertThatThrownBy(() -> new LottoTicketNumber(purchaseAmount.giveLottoTicketNumber(), 10))
                .isInstanceOf(NumberOutOfRangeException.class)
                .hasMessage("구매할 수 있는 수량보다 더 크게 입력하였습니다.");
    }
}
