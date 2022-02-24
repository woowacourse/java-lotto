package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.CustomTicketGenerator;

class TicketsTest {

	private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

	@DisplayName("로또 목록 생성, 개수 일치 확인 테스트")
	@ParameterizedTest
	@MethodSource("provideForGenerateTest")
	void generateTicketsSizeCheckTest(final List<Integer> generatorNumbers, final int ticketCount) {
		customTicketGenerator.initNumbers(generatorNumbers);
		final Tickets tickets = new Tickets(ticketCount, customTicketGenerator);
		assertThat(tickets.getTicketsCount()).isEqualTo(ticketCount);
	}

	public static Stream<Arguments> provideForGenerateTest() {
		return Stream.of(
				Arguments.of(
						Arrays.asList(
								1, 2, 3, 4, 5, 6,
								1, 2, 3, 4, 5, 10,
								1, 2, 3, 6, 7, 15,
								11, 12, 13, 14, 15, 16
						), 4
				)
		);
	}

	@DisplayName("로또 목록 등수 확인 테스트")
	@ParameterizedTest
	@MethodSource("provideForCalculateRanksTest")
	void calculateRanksTest(final List<Integer> winningNumbers,
					  final int bonusNumber,
					  final List<Integer> generatorNumbers,
					  final int ticketCount,
					  final List<Rank> expected) {
		customTicketGenerator.initNumbers(generatorNumbers);
		final Ticket winningTicket = new Ticket(winningNumbers);
		final Ball bonusBall = new Ball(bonusNumber);

		final Tickets tickets = new Tickets(ticketCount, customTicketGenerator);
		final List<Rank> actual = tickets.calculateRanks(winningTicket, bonusBall);
		assertThat(actual).isEqualTo(expected);
	}

	public static Stream<Arguments> provideForCalculateRanksTest() {
		return Stream.of(
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6), 10,
						Arrays.asList(
								1, 2, 3, 4, 5, 6,
								1, 2, 3, 4, 5, 10,
								1, 2, 3, 6, 7, 15,
								11, 12, 13, 14, 15, 16
						), 4,
						Arrays.asList(
								Rank.FIRST_GRADE,
								Rank.SECOND_GRADE,
								Rank.FOURTH_GRADE
						)
				)
		);
	}

}
