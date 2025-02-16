package model.numbers;

import common.NumberGenerator;
import java.util.List;

public class LottoNumbersGenerator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_SIZE = 6;

    public LottoNumbers generate() {
        List<LottoNumber> lottoNumbers = NumberGenerator.generate(NUMBER_SIZE, MIN_NUMBER, MAX_NUMBER).stream()
                .map(LottoNumber::new)
                .toList();
        return new LottoNumbers(lottoNumbers);
    }
}
