package lotto.domain.generate;

import lotto.domain.Lotto;

@FunctionalInterface
public interface LottoFactory {
    Lotto create();
}
