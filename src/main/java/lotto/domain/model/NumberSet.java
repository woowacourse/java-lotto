package lotto.domain.model;

import java.util.HashMap;
import java.util.Map;

import static lotto.domain.model.Number.MAX_LOTTO_NUMBER;
import static lotto.domain.model.Number.checkLottoNumberRange;

public class NumberSet {

    public static final Map<Integer, Number> lottoNumbers = new HashMap<>();

    static {
        for (int i = 1; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.put(i, Number.from(i));
        }
    }

    public static Number of(int lottoNumber) {
        checkLottoNumberRange(lottoNumber);
        return lottoNumbers.get(lottoNumber);
    }

}
