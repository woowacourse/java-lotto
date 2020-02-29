package lotto.domain.lotto.Generator;

import java.util.List;

import lotto.domain.lotto.Lotto;

public interface LottoTicketGenerator {
	List<Lotto> generate();
}
