package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TicketsTest {

    @Test
    void 티켓_생성() {
        Tickets tickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        assertThat(tickets.getTickets().size()).isEqualTo(5);
    }
}
