package lotto.domain.lotto;

import java.util.Objects;
import lotto.exception.InvalidLottoNumberException;

public class LottoNumber {

    public static final int MIN = 1;
    public static final int MAX = 45;

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int value) {
        validateLottoNumber(value);
        return LottoNumberCache.CACHE[value];
    }

    private static void validateLottoNumber(int value) {
        if (value < LottoNumberCache.LOW || value > LottoNumberCache.HIGH) {
            throw new InvalidLottoNumberException();
        }
    }

    private static class LottoNumberCache {

        static final int LOW;
        static final int HIGH;
        static final LottoNumber[] CACHE;

        static {
            LOW = MIN;
            HIGH = MAX;
            CACHE = new LottoNumber[MAX + 1];
            for (int i = MIN; i <= MAX; i++) {
                CACHE[i] = new LottoNumber(i);
            }
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
