package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;

import java.util.Objects;

import static lotto.domain.LottoNumber.LOTTO_LAST_NUMBER;
import static lotto.domain.LottoNumber.LOTTO_START_NUMBER;

public class BonusBall {
    private final LottoNumber number;

    public BonusBall(final int number) {
        if (number <= LOTTO_START_NUMBER || number > LOTTO_LAST_NUMBER) {
            throw new InvalidLottoNumberException("보너스 볼의 범위는 1 ~ 45 입니다.");
        }

        this.number = LottoNumber.getLottoNumber(number);
    }

    public boolean isMatch(final LottoNumber lottoNumber) {
        return number.equals(lottoNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BonusBall bonusBall = (BonusBall) o;
        return Objects.equals(number, bonusBall.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
