package domain;

import java.util.*;

public class LottoNumber {

    private static final int MIN_NUMBER_VALUE = 1;
    private static final int MAX_NUMBER_VALUE = 45;

    private static final Set<LottoNumber> existingNumbers = new HashSet<>();

    private final int value;

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(final int value) {
        validateRange(value);
        LottoNumber lottoNumber = getInstance(value);
        existingNumbers.add(lottoNumber);
        return lottoNumber;
    }

    private static LottoNumber getInstance(int value) {
       return existingNumbers.stream()
            .filter(number -> number.getValue() == value)
            .findAny()
            .orElse(new LottoNumber(value));
    }

    private static void validateRange(final int value) {
        if (value < MIN_NUMBER_VALUE || value > MAX_NUMBER_VALUE) {
            throw new IllegalArgumentException("1에서 45 범위에서 벗어났습니다.");
        }
    }

    public int getValue() {
        return value;
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
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
