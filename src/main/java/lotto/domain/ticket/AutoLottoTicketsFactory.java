package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoTicketsFactory implements LottoTicketsFactory {
	private final LottoTicketFactory ticketGenerator;
	private final LottoCount lottoCount;

	public AutoLottoTicketsFactory(LottoCount lottoCount) {
		this.ticketGenerator = new RandomLottoTicketFactory();
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
