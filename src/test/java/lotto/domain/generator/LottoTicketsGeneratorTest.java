package lotto.domain.generator;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketGenerator;
import lotto.domain.LottoTickets;

class LottoTicketsGeneratorTest {
	@Test
	void createLottoTicketsTest() {
		LottoCount lottoCount = LottoCount.valueOf(5);
		LottoTicketGenerator ticketGenerator = () -> LottoTicket.of("1", "2", "3", "4", "5", "6");
		LottoTicketsGenerator generator = new FixedLottoTicketsGenerator(ticketGenerator, lottoCount);
		LottoTickets lottoTickets = generator.create();
		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("1", "2", "3", "4", "5", "6")
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
		assertThatThrownBy(() -> new ManualLottoTicketsGenerator(manualTicketNumbers, lottoCount))
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
		LottoTicketsGenerator manualLottoTicketsGenerator = new ManualLottoTicketsGenerator(manualTicketNumbers,
			lottoCount);
		LottoTickets lottoTickets = manualLottoTicketsGenerator.create();

		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("2", "5", "6", "7", "8", "9"),
			LottoTicket.of("2", "5", "6", "7", "8", "9"),
			LottoTicket.of("2", "5", "6", "7", "8", "9")
		);
	}

	@Test
	void CompositedTicketsGeneratorTest() {
		List<LottoTicketsGenerator> ticketsGenerators = Arrays.asList(
			new FixedLottoTicketsGenerator(() -> LottoTicket.of("1", "2", "3", "4", "5", "6"), LottoCount.valueOf(3)),
			new FixedLottoTicketsGenerator(() -> LottoTicket.of("11", "12", "13", "14", "15", "16"), LottoCount.valueOf(2)),
			new FixedLottoTicketsGenerator(() -> LottoTicket.of("21", "22", "23", "24", "25", "26"), LottoCount.valueOf(1))
		);
		LottoTicketsGenerator generator = new CompositeLottoTicketsGenerator(ticketsGenerators);
		LottoTickets lottoTickets = generator.create();

		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("1", "2", "3", "4", "5", "6"),
			LottoTicket.of("11", "12", "13", "14", "15", "16"),
			LottoTicket.of("11", "12", "13", "14", "15", "16"),
			LottoTicket.of("21", "22", "23", "24", "25", "26")
		);
	}
}