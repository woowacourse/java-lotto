package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoTicketsGenerator {
	private static final int STARTING_COUNTING_INDEX = 0;

	private final LottoTicketGenerator lottoTicketGenerator;

	public LottoTicketsGenerator(LottoTicketGenerator lottoTicketGenerator) {
		this.lottoTicketGenerator = Objects.requireNonNull(lottoTicketGenerator);
	}

	public LottoTickets createLottosByCount(LottoCount count) {
		List<LottoTicket> lottos = new ArrayList<>();
		for (int currentCount = STARTING_COUNTING_INDEX; count.isNonFullCount(currentCount); currentCount++) {
			lottos.add(lottoTicketGenerator.create());
		}
		return new LottoTickets(lottos);
	}
}
