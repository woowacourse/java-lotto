package model;

import model.error.ErrorType;

public class LottoNumber {

    private final int number;

    public LottoNumber(final int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(final int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorType.로또숫자범위오류.getMessage());
        }
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
        return number;
    }
}
