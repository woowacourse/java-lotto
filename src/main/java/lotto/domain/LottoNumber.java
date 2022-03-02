package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    private final int number;

    public LottoNumber(final int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(final int number) {
        if (isValidRange(number)) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 " + LOWER_BOUND + "~" + UPPER_BOUND + " 사이의 숫자만 가능합니다");
        }
    }

    private boolean isValidRange(final int number) {
        return number < LOWER_BOUND || number > UPPER_BOUND;
    }

    public int getNumber() {
        return number;
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
}
