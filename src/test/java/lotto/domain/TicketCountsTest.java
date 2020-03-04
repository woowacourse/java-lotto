package lotto.domain;

import lotto.exception.TicketCountsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TicketCountsTest {
    @DisplayName("수동으로 구매할 로또 티켓의 수가 전체 로또 티켓의 수보다 클 경우 예외 발생")
    @Test
    void validateCountIsGreaterThanMax() {
        assertThatThrownBy(() -> {
            TicketCounts.fromMoneyAndManualTicketCount(Money.createPurchaseMoney(14000), 15);
        }).isInstanceOf(TicketCountsException.class)
                .hasMessage("구매 가능한 로또 티켓의 수를 초과하였습니다.");
    }

    @DisplayName("수동으로 구매할 로또 티켓의 수가 양수가 아닐 경우 예외 발생")
    @Test
    void validateCountIsGreaterThanMin() {
        assertThatThrownBy(() -> {
            TicketCounts.fromMoneyAndManualTicketCount(Money.createPurchaseMoney(14000), 0);
        }).isInstanceOf(TicketCountsException.class)
                .hasMessage("구매할 로또 티켓의 수는 양수이어야 합니다.");
    }
}