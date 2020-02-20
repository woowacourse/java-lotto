package domain.lottonumber;

import domain.lottonumber.generator.NumberGenerator;

public class LottoNumbersFactory {
    private LottoNumbersFactory() {
        throw new AssertionError();
    }

    public static LottoNumbers createLottoNumbers(NumberGenerator numberGenerator) {
        return new LottoNumbers(numberGenerator.create());
    }
}
