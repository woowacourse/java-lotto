package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_NUMBER_RANGE;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int LOTTO_RANGE_MINIMUM = 1;
    public static final int LOTTO_RANGE_MAXIMUM = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < LOTTO_RANGE_MINIMUM || number > LOTTO_RANGE_MAXIMUM) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

    public static List<LottoNumber> from(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
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
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
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
