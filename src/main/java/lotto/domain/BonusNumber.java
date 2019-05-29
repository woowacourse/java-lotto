package lotto.domain;

import lotto.exceptions.BonusNumberException;

public class BonusNumber extends Number {
    private BonusNumber(int number) {
        super(number);
    }

    public BonusNumber of(int number, WinningLotto winningLotto) {
        if (winningLotto.contains(LottoNumber.of(number))) {
            throw new BonusNumberException("이미 당첨 번호에 있는 번호입니다.");
        }
        return new BonusNumber(number);
    }

    @Override
    public void valid(int lottoNumber) {

    }
}
