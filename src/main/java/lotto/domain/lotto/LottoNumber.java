package lotto.domain.lotto;

import java.util.Objects;

public class LottoNumber {

    public static final int MIN = 1;
    public static final int MAX = 45;

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int value) {
        validateLottoNumber(value);
        return LottoNumberCache.CACHE[value - 1];
    }

    private static void validateLottoNumber(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이의 값이어야 합니다.");
        }
    }

    private static class LottoNumberCache {
        static final int LOW;
        static final int HIGH;
        static final LottoNumber[] CACHE;

        static {
            LOW = MIN;
            HIGH = MAX;
            CACHE = new LottoNumber[(MAX - MIN) + 1];
            for (int i = 0; i < CACHE.length; i++) {
                CACHE[i] = new LottoNumber(i+1);
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
