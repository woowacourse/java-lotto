package lotto;

import java.util.Objects;

public class LottoNumber {
    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final String RANGE_ERROR_MESSAGE = "숫자는 1~45 사이의 숫자여야한다.";

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number >= MIN_NUMBER_RANGE && number <= MAX_NUMBER_RANGE) {
            return;
        }
        throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
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
}
