package lotto.domain.lottogenerator;

import lotto.domain.Lotto;

public class LottoGenerator {
    public static Lotto create(LottoGeneratingStrategy strategy) {
        return new Lotto(strategy.generate());
    }
}
