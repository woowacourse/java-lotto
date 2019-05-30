package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    protected final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        checkValidLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void checkValidLottoNumber(int lottoNumber) {
        if (isInLottoNumberRange(lottoNumber)) {
            throw new InvalidLottoNumberException("번호가 " + MIN_LOTTO_NUMBER + " ~ " + MAX_LOTTO_NUMBER + " 범위를 벗어납니다.");
        }
    }

    private boolean isInLottoNumberRange(int lottoNumber) {
        return lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() && !o.getClass().isAssignableFrom(getClass())) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(lottoNumber, o.lottoNumber);
    }
}