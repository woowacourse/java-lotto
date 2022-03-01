package lotto.domain.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_NUMBERS_RANGE_MESSAGE = "로또 숫자 범위는 1 ~ 45입니다.";

    public static final int MAX_VALUE = 45;
    public static final int MIN_VALUE = 1;

    private static final Map<Integer, LottoNumber> cacheLottoNumbers = new HashMap<>();

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validateRange(number);
        if (!cacheLottoNumbers.containsKey(number)) {
            cacheLottoNumbers.put(number, new LottoNumber(number));
        }
        return cacheLottoNumbers.get(number);
    }

    private static void validateRange(int number) {
        if (number < MIN_VALUE || MAX_VALUE < number) {
            throw new IllegalArgumentException(ERROR_NUMBERS_RANGE_MESSAGE);
        }
    }

    public int get() {
        return number;
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
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
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
