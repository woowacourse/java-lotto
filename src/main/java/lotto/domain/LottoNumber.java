package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_NUMBER_RANGE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int LOTTO_RANGE_MINIMUM = 1;
    public static final int LOTTO_RANGE_MAXIMUM = 45;

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validate(number);

        return CACHE.computeIfAbsent(number, LottoNumber::new);
    }

    private static void validate(int number) {
        if (number < LOTTO_RANGE_MINIMUM || number > LOTTO_RANGE_MAXIMUM) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

    public static List<LottoNumber> from(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber lottoNumber)) {
            return false;
        }
        return Objects.equals(this.number, lottoNumber.number);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

}
