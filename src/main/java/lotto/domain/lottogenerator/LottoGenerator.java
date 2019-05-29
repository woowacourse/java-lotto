package lotto.domain.lottogenerator;

import lotto.domain.Lotto;

import java.util.Objects;

public class LottoGenerator {
    public static Lotto create(LottoGeneratingStrategy strategy) {
        if (Objects.isNull(strategy)) {
            throw new NullPointerException();
        }

        return new Lotto(strategy.generate());
    }
}
