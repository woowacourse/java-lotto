package lotto.domain.generator;

import lotto.domain.Lotto;

@FunctionalInterface
public interface LottoNosGenerator {
    Lotto generate();
}
