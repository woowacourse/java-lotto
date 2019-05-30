package lotto.domain.model;

import java.util.HashMap;
import java.util.Map;

public class NumberSet {
    private static final String LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "로또 번호는 1부터 45까지입니다.";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public static final Map<Integer, Number> lottoNumbers = new HashMap<>();

    static {
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.put(i, new Number(i));
        }
    }

    public static Number of(int lottoNumber) {
        checkLottoNumberRange(lottoNumber);
        return lottoNumbers.get(lottoNumber);
    }

    private static void checkLottoNumberRange(int lottoNumber) {
        if (lottoNumber > MAX_LOTTO_NUMBER || lottoNumber < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }
}
