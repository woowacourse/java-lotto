package domain.lottonumber;

import domain.lottonumber.generator.LottoGenerator;

public class LottoNumbersFactory {
    private LottoNumbersFactory() {
        throw new AssertionError();
    }

    public static LottoNumbers createLottoNumbers(LottoGenerator lottoGenerator) {
        return new LottoNumbers(lottoGenerator.create());
    }
}
