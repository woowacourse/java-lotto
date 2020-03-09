package lotto.domain.number;

import lotto.domain.result.Money;

public interface LottoRoundsGenerable {
    LottoRounds generate(Money money);
}
