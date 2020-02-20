package domain;

import java.util.List;

public class LottoNumbersFactory {
    public static LottoNumbers createLottoNumbers(NumberGenerator numberGenerator) {
        return new LottoNumbers(numberGenerator.create());
    }
}
