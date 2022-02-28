package lotto.domain.ticket.generator;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.ticket.Ticket;

class RandomTicketGeneratorTest {

    private static final int TICKET_DEFAULT_SIZE = 6;
    private static final int TICKET_NUMBER_RANGE_INCLUSIVE_START = 1;
    private static final int TICKET_NUMBER_RANGE_INCLUSIVE_END = 45;

    private final TicketGenerator ticketGenerator = new RandomTicketGenerator();

    @DisplayName("랜덤 생성된 숫자 요소는 6개여야 합니다.")
    @Test
    void sizeTest() {
        final Ticket ticket = ticketGenerator.generateTicket();
        final List<Integer> ballNumbers = ticket.getBallNumbers();
        assertThat(ballNumbers.size()).isEqualTo(TICKET_DEFAULT_SIZE);
    }

    @DisplayName("랜덤 생성된 숫자 요소는 범위 안에 속해 있어야 합니다.")
    @Test
    void rangeTest() {
        final Ticket ticket = ticketGenerator.generateTicket();
        final List<Integer> ballNumbers = ticket.getBallNumbers();
        ballNumbers.forEach(number ->
            assertThat(number).isBetween(TICKET_NUMBER_RANGE_INCLUSIVE_START, TICKET_NUMBER_RANGE_INCLUSIVE_END)
        );
    }

}
