package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;

public class TestLottoTicketsFactory implements LottoTicketsFactory {
	private final LottoTicketFactory ticketGenerator;
	private final LottoCount lottoCount;

	public TestLottoTicketsFactory(LottoTicketFactory ticketGenerator, LottoCount lottoCount) {
		this.ticketGenerator = ticketGenerator;
		this.lottoCount = lottoCount;
	}

	@Override
	public LottoTickets create() {
		List<LottoTicket> result = new ArrayList<>();
		for (int i = 0; lottoCount.isNonFullCount(i); i++) {
			result.add(ticketGenerator.create());
		}
		return new LottoTickets(result);
	}
}
