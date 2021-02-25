package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumbers;

import java.util.ArrayList;
import java.util.List;

import static lotto.utils.AutoLottoGenerator.FROM_INDEX;

public class FixedLottoGenerator implements LottoGenerator {

    private final List<LottoNumbers> numbers = new ArrayList<>();

    public FixedLottoGenerator(List<LottoNumbers> fixedLottoNumbersBundle) {
        numbers.addAll(fixedLottoNumbersBundle);
    }

    @Override
    public Lotto generate() throws IllegalArgumentException {
        LottoNumbers lottoNumbers = numbers.get(FROM_INDEX);
        numbers.remove(FROM_INDEX);
        lottoNumbers.sort();
        return new Lotto(new LottoNumbers(lottoNumbers));
    }
}