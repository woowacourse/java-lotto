package domain;

import java.util.List;

public class LottoTicket {

	private final List<Lotto> lottoTicket;

	public LottoTicket(final List<Lotto> lottoTicket) {
		this.lottoTicket = List.copyOf(lottoTicket);
	}

	public List<Lotto> getLottoTicket() {
		return lottoTicket;
	}
}
