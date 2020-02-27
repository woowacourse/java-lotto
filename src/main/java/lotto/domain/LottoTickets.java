package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(final List<LottoTicket> lottoTickets) {
		this.lottoTickets = lottoTickets;
	}

	public Stream<LottoTicket> stream() {
		return lottoTickets.stream();
	}

	public void add(LottoTicket lottoTicket) {
		this.lottoTickets.add(lottoTicket);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoTickets that = (LottoTickets)o;
		return Objects.equals(lottoTickets, that.lottoTickets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoTickets);
	}
}
