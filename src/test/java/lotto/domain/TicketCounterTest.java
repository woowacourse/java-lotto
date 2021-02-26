package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketCounterTest {

    @Test
    @DisplayName("구매금액에 따라 티켓을 최대로 구매하는지 확인")
    void generateTotalTicket() {
        Ticket ticket = new Ticket(new Money(4000));
        TicketCounter ticketCounter = new TicketCounter(ticket.getCount());
        assertThat(ticketCounter.getCount()).isEqualTo(ticket.getCount());
    }

    @Test
    @DisplayName("티켓의 개수가 줄어드는지 확인")
    void minus() {
        Ticket expected = new Ticket(new Money(3000));
        Ticket ticket = new Ticket(new Money(4000));
        TicketCounter ticketCounter = new TicketCounter(ticket.getCount());
        assertThat(ticketCounter.minus(1).getCount()).isEqualTo(expected.getCount());
    }

    @Test
    @DisplayName("티켓이 0장인지 확인")
    void isZero() {
        Ticket ticket = new Ticket(new Money(1000));
        TicketCounter ticketCounter = new TicketCounter(ticket.getCount());
        assertThat(ticketCounter.minus(1).isZero()).isTrue();
    }

    @Test
    @DisplayName("티켓의 장수는 음의 정수면 안 된다")
    void validateTicketNegative() {
        Ticket ticket = new Ticket(new Money(1000));
        TicketCounter ticketCounter = new TicketCounter(ticket.getCount());
        assertThatThrownBy(() -> ticketCounter.minus(2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(TicketCounter.TOTAL_TICKET_ZERO_LOWER_ERROR_MESSAGE);
    }
}
