package domain;

import java.util.Objects;
import java.util.stream.IntStream;

import domain.constant.LottoConstant;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_MESSAGE_NOT_IN_RANGE = "유효한 로또 번호가 아닙니다.";

    private static final LottoNumber[] LOTTO_NUMBERS = new LottoNumber[LottoConstant.MAXIMUM_VALUE + 1];

    static {
        IntStream.rangeClosed(LottoConstant.MINIMUM_VALUE, LottoConstant.MAXIMUM_VALUE)
            .forEach(number -> LOTTO_NUMBERS[number] = new LottoNumber(number));
    }

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(final int number) {
        validateInRange(number);
        return LOTTO_NUMBERS[number];
    }

    private static void validateInRange(int value) {
        if (value < LottoConstant.MINIMUM_VALUE || value > LottoConstant.MAXIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_RANGE);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.value - other.value;
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
