package domain.lottoGame;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    protected static final int NUMBER_MIN = 1;
    protected static final int NUMBER_MAX = 45;

    private static final LottoNumber[] LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = new LottoNumber[NUMBER_MAX + 1];

        for (int i = NUMBER_MIN; i <= NUMBER_MAX; i++) {
            LOTTO_NUMBERS[i] = new LottoNumber(i);
        }
    }

    private final int value;

    private LottoNumber(int value) {
        validateLottoNumber(value);
        this.value = value;
    }

    public static LottoNumber from(int value) {
        validateLottoNumber(value);
        return LOTTO_NUMBERS[value];
    }

    private static void validateLottoNumber(int value) {
        if (value < NUMBER_MIN || value > NUMBER_MAX) {
            throw new IllegalArgumentException("로또 숫자 범위 외 숫자입니다.");
        }
    }

    public int getNumber() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.value - o.value;
    }
}
