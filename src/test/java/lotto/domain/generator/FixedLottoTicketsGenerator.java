package lotto.domain.generator;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketGenerator;
import lotto.domain.LottoTickets;

public class FixedLottoTicketsGenerator implements LottoTicketsGenerator {
	private final LottoTicketGenerator ticketGenerator;
	private final LottoCount lottoCount;

	public FixedLottoTicketsGenerator(LottoTicketGenerator ticketGenerator, LottoCount lottoCount) {
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
