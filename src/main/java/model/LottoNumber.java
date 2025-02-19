package model;

import error.ErrorType;
import java.util.HashMap;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>(); // TODO. 멀티 쓰레드 환경일 시 ConcurrentHashMap 사용 고려

    private final int number;

    private LottoNumber(final int number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber of(final int number) {
        return CACHE.computeIfAbsent(number, LottoNumber::new);
    }

    private void validateRange(final int number) {
        if (number < Lotto.LOTTO_NUMBER_START_INCLUSIVE || number > Lotto.LOTTO_NUMBER_END_INCLUSIVE) {
            throw new IllegalArgumentException(ErrorType.LOTTO_NUMBER_RANGE.getMessage());
        }
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

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public int compareTo(final LottoNumber o) {
        return this.number - o.number;
    }

    public int getNumber() {
        return number;
    }
}
