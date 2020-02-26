package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ManualLottoTicketsFactoryTest {
	@Test
	void generate_InputLottoTickets_GenerateLottoTicketList() {
		List<String> inputLottoTickets = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 3, 4, 5, 6, 7",
			"3, 4, 5, 6, 7, 8"
		);
		assertThat(ManualLottoTicketsFactory.generate(inputLottoTickets))
			.isInstanceOf(LottoTickets.class);
	}
}
