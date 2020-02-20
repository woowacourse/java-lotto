package lotto.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LottoTickets implements Iterable<LottoTicket> {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets = Collections.unmodifiableList(lottoTickets);
	}

	public int size() {
		return lottoTickets.size();
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}

	@Override
	public Iterator<LottoTicket> iterator() {
		return lottoTickets.iterator();
	}
}
