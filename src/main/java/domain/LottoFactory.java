package domain;

import domain.generator.LottoGenerator;

public class LottoFactory {

    public static Lotto generateLotto(final LottoGenerator lottoGenerator) {
        return lottoGenerator.generate();
    }
}

