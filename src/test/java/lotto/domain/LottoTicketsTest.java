package lotto.domain;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class LottoTicketsTest {
    @DisplayName("size메소드 동작 확인")
    @Test
    void size메소드_동작_확인() {
        LottoTicket ticket1 = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket ticket2 = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket ticket3 = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<LottoTicket> ticketBundle = Arrays.asList(ticket1, ticket2, ticket3);

        LottoTickets tickets = new LottoTickets(ticketBundle);

        Assertions.assertThat(tickets.size()).isEqualTo(ticketBundle.size());
    }
}