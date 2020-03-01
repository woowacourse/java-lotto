package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsFactoryTest {
	@DisplayName("로또 팩토리 임의 구현체를 통한 로또 생성 확인")
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

	@DisplayName("수동 로또 팩토리를 통해 생성된 로또 확인")
	@Test
	void createLottoTicketsByManualTest2() {
		List<List<Integer>> manualTicketNumbers = Arrays.asList(
			Arrays.asList(1, 2, 3, 4, 5, 6),
			Arrays.asList(2, 5, 6, 7, 8, 9),
			Arrays.asList(2, 5, 6, 7, 8, 9),
			Arrays.asList(2, 5, 6, 7, 8, 9)
		);
		LottoTicketsFactory manualLottoTicketsFactory = new ManualLottoTicketsFactory(manualTicketNumbers);
		LottoTickets lottoTickets = manualLottoTicketsFactory.create();

		assertThat(lottoTickets.getLottoTickets()).containsExactly(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(2, 5, 6, 7, 8, 9),
			LottoTicket.of(2, 5, 6, 7, 8, 9),
			LottoTicket.of(2, 5, 6, 7, 8, 9)
		);
	}
}