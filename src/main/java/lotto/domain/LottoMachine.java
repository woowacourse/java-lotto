package lotto.domain;

import lotto.LottoNumberGenerator;

public class LottoMachine {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(final LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lotto createLotto() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
