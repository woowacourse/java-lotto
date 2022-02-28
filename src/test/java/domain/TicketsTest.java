package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class TicketsTest {

    @Test
    void 티켓_생성() {
        Tickets tickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        assertThat(tickets.getTickets().size()).isEqualTo(5);
    }

    @Test
    void 티켓_추가() {
        Tickets tickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        Tickets addTickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        tickets.add(addTickets);
        assertThat(tickets.getTickets().size()).isEqualTo(10);
    }
}
