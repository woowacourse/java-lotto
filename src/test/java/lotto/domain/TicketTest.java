package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketTest {

    @Test
    @DisplayName("티켓을 구매 못하는 경우 확인")
    void validateMinimumTicketPrice() {
        assertThatThrownBy(() -> new Ticket(new Money(900)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("원 이상");
    }

    @Test
    @DisplayName("수동 구매가 전체 티켓 수를 넘을 때")
    void validateManualTicket() {
        Ticket ticket = new Ticket(new Money(14000));
        assertThatThrownBy(() -> ticket.setManualCount(15)).isInstanceOf(IllegalArgumentException.class)
                                                           .hasMessageContaining("수동 로또");
    }
}
