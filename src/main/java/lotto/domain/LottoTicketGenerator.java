package lotto.domain;

import lotto.domain.LottoTicket;

@FunctionalInterface
public interface LottoTicketGenerator {
	LottoTicket create();
}
