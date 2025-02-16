package model;

import constans.ErrorType;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int number;

    public LottoNumber(final int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(final int number) {
        if (number < Lotto.LOTTO_NUMBER_START_INCLUSIVE || number > Lotto.LOTTO_NUMBER_END_INCLUSIVE) {
            throw new IllegalArgumentException(ErrorType.LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    @Override
    public boolean equals(final Object o) {
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

    @Override
    public int compareTo(final LottoNumber target) {
        return this.number - target.number;
    }

    public int getNumber() {
        return number;
    }
}
