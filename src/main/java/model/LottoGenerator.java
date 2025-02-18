package model;

import static constant.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Set;
import java.util.TreeSet;

public class LottoGenerator {
    private final LottoNumberPicker lottoNumberPicker;

    public LottoGenerator(LottoNumberPicker lottoNumberPicker) {
        this.lottoNumberPicker = lottoNumberPicker;
    }

    public Lotto generateLotto() {
        Set<LottoNumber> numbers = new TreeSet<>();
        while (numbers.size() != LOTTO_NUMBER_COUNT) {
            numbers.add(lottoNumberPicker.pickRandomNumber());
        }
        return new Lotto(numbers);
    }
}
