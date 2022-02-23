package domain;

import utils.Validator;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(final String inputNumber) {
        validateInputLottoNumber(inputNumber);
        this.number = Integer.parseInt(inputNumber);
        validateLottoNumber(this.number);
    }

    private void validateInputLottoNumber(final String inputNumber) {
        Validator.checkNullOrEmpty(inputNumber);
        Validator.checkFormat(inputNumber);
    }

    public LottoNumber(final int inputNumber) {
        this.number = inputNumber;
    }

    private void validateLottoNumber(final int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호가 유효한 범위(1-45)가 아닙니다.");
        }
    }

    @Override
    public int compareTo(final LottoNumber otherLottoNumber) {
        return this.number - otherLottoNumber.number;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final LottoNumber that = (LottoNumber) object;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

