package lotto.domain;

import java.util.Objects;
import lotto.exception.InvalidLottoNumberException;

public class LottoNumber {

    private static final LottoNumber[] lottoNumberCache;
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    private final int number;

    static {
        LottoNumber[] lottoCache = new LottoNumber[MAX_BOUND];
        for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
            lottoCache[i - 1] = new LottoNumber(i);
        }
        lottoNumberCache = lottoCache;
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        validate(number);
        return lottoNumberCache[number - 1];
    }

    private static void validate(final int number) {
        if (number < MIN_BOUND || number > MAX_BOUND) {
            throw new InvalidLottoNumberException();
        }
    }

    public int getLottoNumber() {
        return number;
    }
}
