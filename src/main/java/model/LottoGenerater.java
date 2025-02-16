package model;

import static constant.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Set;
import java.util.TreeSet;

public class LottoGenerater {
    private final LottoNumberPicker lottoNumberPicker;

    public LottoGenerater(LottoNumberPicker lottoNumberPicker) {
        this.lottoNumberPicker = lottoNumberPicker;
    }

    public Lotto generateLotto() {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        while (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(lottoNumberPicker.pickRandomNumber());
        }
        return new Lotto(lottoNumbers);
    }
}
