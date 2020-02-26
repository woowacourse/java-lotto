package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.domain.result.LottoRank;
import lotto.domain.result.WinningLotto;

public class LottoTickets implements Iterable<LottoTicket> {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(lottoTickets)));
	}

	List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}

	public List<LottoRank> findLottoRanks(WinningLotto winningLotto) {
		return lottoTickets.stream()
			.map(winningLotto::calculateRank)
			.collect(Collectors.toList());
	}

	@Override
	public Iterator<LottoTicket> iterator() {
		return lottoTickets.iterator();
	}
}
