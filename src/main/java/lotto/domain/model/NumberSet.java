package lotto.domain.model;

import java.util.HashMap;
import java.util.Map;

public class NumberSet {
    private static final int MAX_LOTTO_NUMBER = 45;

    public static final Map<Integer, Number> lottoNumbers = new HashMap<>();

    static {
        for (int i = 1; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.put(i, new Number(i));
        }
    }

    public static Number of(int lottoNumber) {
        Number.checkLottoNumberRange(lottoNumber);
        return lottoNumbers.get(lottoNumber);
    }

}
