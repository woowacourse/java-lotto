package lotto.domain.generator;

import lotto.domain.LottoTicket;

@FunctionalInterface
public interface LottoTicketGenerator {
	LottoTicket create();
}
