package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTicketsFactoryTest {
	@Test
	void createLottoTicketsTest() {
		LottoCount lottoCount = LottoCount.valueOf(5);
		LottoTicketFactory ticketGenerator = () -> LottoTicket.of(1, 2, 3, 4, 5, 6);
		LottoTicketsFactory generator = new TestLottoTicketsFactory(ticketGenerator, lottoCount);
		LottoTickets lottoTickets = generator.create();
		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6)
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {3, 5, 6})
	void ManualGenerateTest(int lottoCountValue) {
		LottoCount lottoCount = LottoCount.valueOf(lottoCountValue);
		List<String> manualTicketNumbers = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 5, 6, 7, 8, 9",
			"2, 5, 6, 7, 8, 9",
			"2, 5, 6, 7, 8, 9"
		);
		assertThatThrownBy(() -> new ManualLottoTicketsFactory(manualTicketNumbers, lottoCount))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void createLottoTicketsByManualTest() {
		List<String> manualTicketNumbers = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 5, 6, 7, 8, 9",
			"2, 5, 6, 7, 8, 9",
			"2, 5, 6, 7, 8, 9"
		);
		LottoCount lottoCount = LottoCount.valueOf(4);
		LottoTicketsFactory manualLottoTicketsFactory = new ManualLottoTicketsFactory(manualTicketNumbers,
			lottoCount);
		LottoTickets lottoTickets = manualLottoTicketsFactory.create();

		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(2, 5, 6, 7, 8, 9),
			LottoTicket.of(2, 5, 6, 7, 8, 9),
			LottoTicket.of(2, 5, 6, 7, 8, 9)
		);
	}

	@Test
	void CompositedTicketsGeneratorTest() {
		List<LottoTicketsFactory> ticketsGenerators = Arrays.asList(
			new TestLottoTicketsFactory(() -> LottoTicket.of(1, 2, 3, 4, 5, 6), LottoCount.valueOf(3)),
			new TestLottoTicketsFactory(() -> LottoTicket.of(11, 12, 13, 14, 15, 16),
				LottoCount.valueOf(2)),
			new TestLottoTicketsFactory(() -> LottoTicket.of(21, 22, 23, 24, 25, 26),
				LottoCount.valueOf(1))
		);
		LottoTicketsFactory generator = new CompositeLottoTicketsFactory(ticketsGenerators);
		LottoTickets lottoTickets = generator.create();

		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoTicket.of(21, 22, 23, 24, 25, 26)
		);
	}
}