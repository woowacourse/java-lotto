package lotto.domain.lotto;

import lotto.exception.LottoNumberOutOfBoundsException;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_BOUNDARY = 1;
    public static final int MAX_BOUNDARY = 45;

    private static final Map<Integer, LottoNumber> creators;

    static {
        creators = IntStream.rangeClosed(MIN_BOUNDARY, MAX_BOUNDARY)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), LottoNumber::new));
    }

    private int number;

    private LottoNumber(int number) {
        if (number < MIN_BOUNDARY || number > MAX_BOUNDARY) {
            throw new LottoNumberOutOfBoundsException("범위를 벗어났습니다.");
        }
        this.number = number;
    }

    public static LottoNumber getNumber(int number) {
        if (Objects.isNull(creators.get(number))) {
            throw new NullPointerException();
        }
        return creators.get(number);
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
    public int compareTo(LottoNumber lottoNumber) {
        if (number > lottoNumber.number) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
