package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final String RANGE_ERROR_MESSAGE = "숫자는 1~45 사이의 숫자여야한다.";

    private static final List<LottoNumber> CACHE;

    private final int number;

    static {
        CACHE = Collections.unmodifiableList(
                IntStream.range(MIN_NUMBER_RANGE, MAX_NUMBER_RANGE)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static List<LottoNumber> getCache() {
        return new ArrayList<>(CACHE);
    }

    private void validateRange(int number) {
        if (number >= MIN_NUMBER_RANGE && number <= MAX_NUMBER_RANGE) {
            return;
        }
        throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
    }

    @Override
    public boolean equals(Object o) {
        return number == ((LottoNumber) o).number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(number, lottoNumber.number);
    }
}
