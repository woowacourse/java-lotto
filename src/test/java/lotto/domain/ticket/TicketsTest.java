package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;

class TicketsTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

    @DisplayName("주어진 로또 개수만큼 로또 번호를 생성해야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 개수 : {2}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForGenerateTest")
    void generateTicketsValueCheckTest(final List<Ticket> customTickets,
                                       final int ticketCount) {
        customTicketGenerator.initTickets(customTickets);
        final Tickets tickets = Tickets.generateTickets(ticketCount, customTicketGenerator);
        compareTickets(tickets.getTickets(), customTickets);
    }

    private void compareTickets(final List<Ticket> actualTickets, final List<Ticket> expectedTickets) {
        for (int i = 0; i < actualTickets.size(); i++) {
            final List<Integer> actualBallNumbers = actualTickets.get(i).getBallNumbers();
            final List<Integer> expectedBallNumbers = expectedTickets.get(i).getBallNumbers();
            assertThat(actualBallNumbers).isEqualTo(expectedBallNumbers);
        }
    }

    @DisplayName("계산된 당첨 등수 목록은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 목록 : {4}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForCalculateRanksTest")
    void calculateRanksTest(final List<Integer> winningNumbers,
                            final int bonusNumber,
                            final List<Ticket> preparedTickets,
                            final List<Rank> expectedRanks) {
        final Tickets tickets = Tickets.generateTickets(preparedTickets);
        final WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);

        final List<Rank> actualRanks = tickets.calculateRanks(winningTicket);
        assertThat(actualRanks).isEqualTo(expectedRanks);
    }

}
