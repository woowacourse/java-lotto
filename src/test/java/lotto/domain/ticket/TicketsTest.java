package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.dto.TicketDto;

class TicketsTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

    @DisplayName("로또 목록 생성, 개수 일치 확인 테스트")
    @ParameterizedTest(name = "[{index}] 로또 개수 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForGenerateTest")
    void generateTicketsSizeCheckTest(final List<TicketDto> generatedTickets, final int ticketCount) {
        customTicketGenerator.initNumbers(generatedTickets);
        final Tickets tickets = new Tickets(ticketCount, customTicketGenerator);

        final List<TicketDto> ticketDtos = tickets.getTicketDtos();
        assertThat(ticketDtos.size()).isEqualTo(ticketCount);
    }

    @DisplayName("당첨 등수 확인 테스트")
    @ParameterizedTest(name = "[{index}] 당첨 등수 : {4}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForCalculateRanksTest")
    void calculateRanksTest(final TicketDto winningTicketNumbers,
                            final int bonusNumber,
                            final List<TicketDto> generatedTickets,
                            final int ticketCount,
                            final List<Rank> expected) {
        customTicketGenerator.initNumbers(generatedTickets);
        final Tickets tickets = new Tickets(ticketCount, customTicketGenerator);

        final Ticket winningTicket = new Ticket(winningTicketNumbers.getBallNumbers());
        final Ball bonusBall = new Ball(bonusNumber);

        final List<Rank> actual = tickets.calculateRanks(winningTicket, bonusBall);
        assertThat(actual).isEqualTo(expected);
    }

}
