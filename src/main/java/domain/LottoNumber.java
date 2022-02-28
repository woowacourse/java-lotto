package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {

    private static final int LOTTO_NUMBER_MAXIMUM = 45;
    private static final int LOTTO_NUMBER_MINIMUM = 1;
    private static final Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>();

    private int number;

    static {
        for (int lottoNumber = LOTTO_NUMBER_MINIMUM; lottoNumber <= LOTTO_NUMBER_MAXIMUM; lottoNumber++) {
            lottoNumberCache.put(lottoNumber, new LottoNumber());
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateNumberRange(number);
        return lottoNumberCache.get(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    private static void validateNumberRange(Integer lottoNumber) {
        if (lottoNumber > LOTTO_NUMBER_MAXIMUM || lottoNumber < LOTTO_NUMBER_MINIMUM) {
            throw new IllegalArgumentException();
        }
    }
}
