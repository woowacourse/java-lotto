package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CompositeLottoTicketsFactoryTest {
	@Test
	void name() {
		LottoTicketsFactory factory = new TestLottoTicketsFactory(() -> LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoCount.valueOf(3));
		LottoTicketsFactory secondFactory = new TestLottoTicketsFactory(() -> LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoCount.valueOf(2));
		CompositeLottoTicketsFactory compositeLottoTicketsFactory = new CompositeLottoTicketsFactory(factory,
			secondFactory);

		LottoTickets compositedLottoTickets = compositeLottoTicketsFactory.create();
		assertThat(compositedLottoTickets.getLottoTickets()).containsExactlyInAnyOrder(
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(1, 2, 3, 4, 5, 6),
			LottoTicket.of(11, 12, 13, 14, 15, 16),
			LottoTicket.of(11, 12, 13, 14, 15, 16)
		);
	}
}
