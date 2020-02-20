package domain.lottonumber;

import domain.lottonumber.generator.NumberGenerator;

public class LottoNumbersFactory {
    public static LottoNumbers createLottoNumbers(NumberGenerator numberGenerator) {
        return new LottoNumbers(numberGenerator.create());
    }
}
