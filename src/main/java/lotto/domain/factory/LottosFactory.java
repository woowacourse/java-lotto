package lotto.domain.factory;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class LottosFactory {
	private static final int START_INDEX = 0;

	private final LottoFactory lottoFactory;

	public LottosFactory(LottoFactory lottoFactory) {
		this.lottoFactory = lottoFactory;
	}

	public LottoTickets createLottosByCount(LottoCount count) {
		List<LottoTicket> lottos = new ArrayList<>();
		for (int i = START_INDEX; count.isNonFullCount(i); i++) {
			lottos.add(lottoFactory.create());
		}
		return new LottoTickets(lottos);
	}
}
