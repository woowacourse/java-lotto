package domain;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final String LOTTO_NUMBER_RANGE_MESSAGE =
        "로또 번호가 유효한 범위(" + LOTTO_NUMBER_MIN + "-" + LOTTO_NUMBER_MAX + ")가 아닙니다.";

    private final int number;

    public LottoNumber(final String inputNumber) {
        this.number = Integer.parseInt(inputNumber);
        validateLottoNumberRange(this.number);
    }

    public LottoNumber(final int inputNumber) {
        this.number = inputNumber;
    }

    private void validateLottoNumberRange(final int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_MESSAGE);
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

