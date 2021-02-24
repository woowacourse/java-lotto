package lotto.domain.production;

import lotto.domain.ticket.Lotto;

public interface LottoGenerator {
	Lotto createLotto();
}
