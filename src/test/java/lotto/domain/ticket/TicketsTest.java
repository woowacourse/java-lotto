package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.ticket.generator.TicketNumbers;
import lotto.dto.TicketDto;

class TicketsTest {

	private static final String PROVIDER_PATH = "lotto.domain.ticket.provider.TicketsTestProvider#";

	private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

	@DisplayName("로또 목록 생성, 개수 일치 확인 테스트")
	@ParameterizedTest(name = "[{index}] 로또 개수 : {1}")
	@MethodSource(PROVIDER_PATH + "provideForGenerateTest")
	void generateTicketsSizeCheckTest(final List<TicketNumbers> generatedTickets, final int ticketCount) {
		customTicketGenerator.initNumbers(generatedTickets);
		final Tickets tickets = new Tickets(ticketCount, customTicketGenerator);

		final List<TicketDto> ticketDtos = tickets.getTicketDtos();
		assertThat(ticketDtos.size()).isEqualTo(ticketCount);
	}

	@DisplayName("당첨 등수 확인 테스트")
	@ParameterizedTest(name = "[{index}] 당첨 등수 : {4}")
	@MethodSource(PROVIDER_PATH + "provideForCalculateRanksTest")
	void calculateRanksTest(final TicketNumbers winningTicketNumbers,
					  final int bonusNumber,
					  final List<TicketNumbers> generatedTickets,
					  final int ticketCount,
					  final List<Rank> expected) {
		customTicketGenerator.initNumbers(generatedTickets);
		final Tickets tickets = new Tickets(ticketCount, customTicketGenerator);

		final Ticket winningTicket = new Ticket(winningTicketNumbers.getNumbers());
		final Ball bonusBall = new Ball(bonusNumber);

		final List<Rank> actual = tickets.calculateRanks(winningTicket, bonusBall);
		assertThat(actual).isEqualTo(expected);
	}

}
