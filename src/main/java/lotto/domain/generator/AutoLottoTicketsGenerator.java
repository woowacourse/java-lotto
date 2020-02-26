package lotto.domain.generator;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketGenerator;
import lotto.domain.LottoTickets;
import lotto.domain.RandomLottoTicketGenerator;

public class AutoLottoTicketsGenerator implements LottoTicketsGenerator {
	private final LottoTicketGenerator ticketGenerator;
	private final LottoCount lottoCount;

	public AutoLottoTicketsGenerator(LottoCount lottoCount) {
		this.ticketGenerator = new RandomLottoTicketGenerator();
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
