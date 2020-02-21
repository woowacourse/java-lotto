package lotto.domain.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

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
