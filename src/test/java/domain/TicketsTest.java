package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketsTest {

    @Test
    void 티켓_생성() {
        Tickets tickets = new Tickets(5);
        Assertions.assertThat(tickets.getTickets().size()).isEqualTo(5);
    }
}
