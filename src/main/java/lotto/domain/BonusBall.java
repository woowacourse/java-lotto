package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;

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
}
