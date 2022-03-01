package domain;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_MESSAGE_NOT_IN_RANGE = "유효한 로또 번호가 아닙니다.";

    private static final int MINIMUM_VALUE = 1;
    private static final int MAXIMUM_VALUE = 45;

    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(MINIMUM_VALUE, MAXIMUM_VALUE).boxed()
            .collect(Collectors.toMap(number -> number, LottoNumber::new));
    }

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(final int number) {
        validateInRange(number);

        return LOTTO_NUMBERS.get(number);
    }

    private static void validateInRange(int value) {
        if (value < MINIMUM_VALUE || value > MAXIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_RANGE);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
