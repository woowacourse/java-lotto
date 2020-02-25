package lotto.domain;

import lotto.exception.LottoNumberException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final List<LottoNumber> numbers;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    static {
        numbers = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private static void validateNumberRange(int number) {
        if (isLessThanMin(number) || isGreaterThanMax(number)) {
            throw new LottoNumberException();
        }
    }

    private static boolean isLessThanMin(int number) {
        return number < MIN_NUMBER;
    }

    private static boolean isGreaterThanMax(int number) {
        return number > MAX_NUMBER;
    }

    public static List<LottoNumber> values() {
        return numbers;

    }

    @Override
    public int compareTo(LottoNumber other) {
        return number - other.number;
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
    public String toString() {
        return String.valueOf(number);
    }
}
