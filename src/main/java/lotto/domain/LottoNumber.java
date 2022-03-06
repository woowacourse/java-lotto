package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_NUMBERS_RANGE_MESSAGE = "로또 숫자 범위는 1 ~ 45입니다.";

    public static final int LOTTO_MINIMUM = 1;
    public static final int LOTTO_MAXIMUM = 45;

    private static final Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>();

    private final int number;

    private LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        if (lottoNumberCache.containsKey(number)) {
            return lottoNumberCache.get(number);
        }
        LottoNumber newLottoNumber = new LottoNumber(number);
        lottoNumberCache.put(number, newLottoNumber);
        return newLottoNumber;
    }

    private void validateRange(int number) {
        if (number < LOTTO_MINIMUM || LOTTO_MAXIMUM < number) {
            throw new IllegalArgumentException(ERROR_NUMBERS_RANGE_MESSAGE);
        }
    }

    public int getNumber() {
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
