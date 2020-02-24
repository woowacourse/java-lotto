package lotto.domain.lottoTicket;

import lotto.util.InvalidLottoNumberException;

import java.util.Objects;

public class LottoNumber {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateNumberScope(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateNumberScope(int lottoNumber) {
        if (lottoNumber > MAX_LOTTO_NUMBER || lottoNumber < MIN_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException(lottoNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public String toString() {
        return Integer.toString(lottoNumber);
    }
}
