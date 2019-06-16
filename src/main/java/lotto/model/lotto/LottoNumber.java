package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    static final int MIN_LOTTO_NUMBER = 1;
    static final int MAX_LOTTO_NUMBER = 45;

    private final int lottoNumber;

    LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    static LottoNumber from(int lottoNumber) {
        checkValidLottoNumber(lottoNumber);
        return new LottoNumber(lottoNumber);
    }

    private static void checkValidLottoNumber(int lottoNumber) {
        if (isInLottoNumberRange(lottoNumber)) {
            throw new InvalidLottoNumberException("번호가 " + MIN_LOTTO_NUMBER + " ~ " + MAX_LOTTO_NUMBER + " 범위를 벗어납니다.");
        }
    }

    private static boolean isInLottoNumberRange(int lottoNumber) {
        return lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER;
    }

    public int getLottoNumber() {
        return lottoNumber;
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