package domain.lottonumber;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final String ERROR_BOUND_MESSAGE = "1부터 45 사이의 숫자만 입력 가능합니다.";
    private static final int LOTTO_UNDER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final Map<Integer, LottoNumber> cache = new HashMap<>();

    static {
        for (int i = LOTTO_UNDER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    private int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    private static void validateBound(int number) {
        if (number < LOTTO_UNDER_BOUND || number > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException(ERROR_BOUND_MESSAGE);
        }
    }

    public static LottoNumber of(int number) {
        validateBound(number);
        return cache.get(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass()) {
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

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
