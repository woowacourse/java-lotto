package domain.lottonumber;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    static final int MIN_NUMBER = 1;
    static final int MAX_NUMBER = 45;
    private static final LottoNumber[] cache;

    private final int number;

    static {
        cache = new LottoNumber[MAX_NUMBER - MIN_NUMBER + 1];
        int cacheIndex;

        for (int number = 1; number <= MAX_NUMBER; number++) {
            cacheIndex = getCacheIndexOfLottoNumberMatchedWith(number);
            cache[cacheIndex] = new LottoNumber(number);
        }
    }

    private static int getCacheIndexOfLottoNumberMatchedWith(int number) {
        return number - MIN_NUMBER;
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validateNumberRange(number);
        return cache[getCacheIndexOfLottoNumberMatchedWith(number)];
    }

    private static void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalLottoNumberException();
        }
    }

    boolean isOf(int number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
