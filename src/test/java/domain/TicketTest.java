package domain;

import static error.ErrorMessage.INVALID_TICKET_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @DisplayName("티켓이 천 원 단위가 아닐 경우, 예외가 발생해야 한다")
    @Test
    void invalid_ticket_price() {
        assertThatThrownBy(() -> {
            Ticket ticket = Ticket.create(1200);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_TICKET_PRICE.getMessage());
    }

    @DisplayName("티켓이 천 원 단위일 경우, 성공적으로 티켓 객체가 생성되어야 한다")
    @Test
    void valid_ticket_price() {
        Ticket ticket = Ticket.create(5000);
        int expectedTicketAmount = 5;
        assertThat(ticket.getQuantity()).isEqualTo(expectedTicketAmount);
    }

}
