package lotto.domain.factory;

import lotto.domain.LottoTicket;

@FunctionalInterface
public interface LottoFactory {
	LottoTicket create();
}
