package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;
import lotto.dto.TicketDto;

class TicketsTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

    private Tickets generateTickets(final List<TicketDto> generatedTickets, final int ticketCount) {
        customTicketGenerator.initTickets(generatedTickets);
        return new Tickets(ticketCount, customTicketGenerator);
    }

    @DisplayName("로또는 주어진 개수만큼 생성되어야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 개수 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForGenerateTest")
    void generateTicketsSizeCheckTest(final List<TicketDto> generatedTickets, final int ticketCount) {
        final Tickets tickets = generateTickets(generatedTickets, ticketCount);
        assertThat(tickets.getSize()).isEqualTo(ticketCount);
    }

    @DisplayName("계산된 당첨 등수 목록은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 목록 : {4}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForCalculateRanksTest")
    void calculateRanksTest(final List<Integer> winningNumbers,
                            final int bonusNumber,
                            final List<TicketDto> generatedTickets,
                            final List<Rank> expected) {
        final int ticketCount = generatedTickets.size();
        final Tickets tickets = generateTickets(generatedTickets, ticketCount);
        final WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);

        final List<Rank> actual = tickets.calculateRanks(winningTicket);
        assertThat(actual).isEqualTo(expected);
    }

}
