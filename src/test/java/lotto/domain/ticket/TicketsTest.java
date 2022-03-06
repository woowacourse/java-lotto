package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;
import lotto.domain.winning.WinningTicket;

class TicketsTest {

    @DisplayName("계산된 당첨 등수 목록은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 목록 : {3}")
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
