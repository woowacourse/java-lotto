package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LottoTickets implements Iterable<LottoTicket> {
	private static final String NULL_LOTTOS_EXCEPTION_MESSAGE = "결과 데이터가 null 이라네";

	private final List<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		validateNullOrEmpty(lottoTickets);
		this.lottoTickets = Collections.unmodifiableList(new ArrayList<>(lottoTickets));
	}

	private void validateNullOrEmpty(List<LottoTicket> lottoTickets) {
		if (Objects.isNull(lottoTickets) || lottoTickets.isEmpty()) {
			throw new NullPointerException(NULL_LOTTOS_EXCEPTION_MESSAGE);
		}
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}

	@Override
	public Iterator<LottoTicket> iterator() {
		return lottoTickets.iterator();
	}
}
