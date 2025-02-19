package domain;

import error.ErrorMessage;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validateNumberInRange(number);
        this.number = number;
    }

    private void validateNumberInRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) {
            return false;
        }

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
