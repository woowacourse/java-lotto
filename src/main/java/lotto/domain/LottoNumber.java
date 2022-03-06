package lotto.domain;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private static Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE;
    private final int number;

    private LottoNumber(final int number) {
        checkNumberRightRange(number);
        this.number = number;
    }

    public synchronized static LottoNumber valueOf(final int number) {
        checkNumberRightRange(number);
        if (LOTTO_NUMBER_CACHE == null) {
            LOTTO_NUMBER_CACHE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                    .boxed()
                    .collect(Collectors.toMap(lottoNumber -> lottoNumber, LottoNumber::new));
        }
        return LOTTO_NUMBER_CACHE.get(number);
    }

    private static void checkNumberRightRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 입력값이 1 이상 45 이하여야 합니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
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
    public String toString() {
        return String.valueOf(number);
    }
}
