package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

	private final List<Lotto> lottoTicket;

	public LottoTicket(final List<Lotto> lottoTicket) {
		this.lottoTicket = new ArrayList<>(lottoTicket);
	}

	public List<Lotto> getLottoTicket() {
		return Collections.unmodifiableList(lottoTicket);
	}
}
