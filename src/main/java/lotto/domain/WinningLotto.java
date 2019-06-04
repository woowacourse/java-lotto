package lotto.domain;

import lotto.domain.exception.InvalidWinningLottoException;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validWinningLotto(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.matchNumber(bonusNumber)) {
            throw new InvalidWinningLottoException("로또와 보너스 넘버는 중복될 수 없습니다.");
        }
    }

    Rank match(Lotto lotto) {
        return Rank.valueOf(lotto.matchNumbers(winningLotto), lotto.matchNumber(bonusNumber));
    }
}
