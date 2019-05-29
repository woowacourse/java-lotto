package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    protected final int number;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public LottoNumber(int number) {
        checkValidLottoNumber(number);
        this.number = number;
    }

    private void checkValidLottoNumber(int number) {
        if (isInLottoNumberRange(number)) {
            throw new InvalidLottoNumberException("번호가 " + MIN_LOTTO_NUMBER + " ~ " + MAX_LOTTO_NUMBER + " 범위를 벗어납니다.");
        }
    }

    private boolean isInLottoNumberRange(int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() && !o.getClass().isAssignableFrom(getClass())) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}