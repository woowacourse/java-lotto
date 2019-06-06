package lotto.domain.lotto;

import lotto.exception.DuplicateLottoNumberException;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.bonusNumber = LottoNumber.getNumber(bonusNumber);
        if (winningLotto.contains(this.bonusNumber)) {
            throw new DuplicateLottoNumberException("로또숫자와 보너스숫자가 중복으로 입력되었습니다");
        }
        this.winningLotto = winningLotto;
    }

    Rank match(Lotto lotto) {
        return Rank.valueOf(countMatchedLottoNumber(lotto), contains(lotto));
    }

    private int countMatchedLottoNumber(Lotto lotto) {
        return lotto.countMatchedLottoNumber(winningLotto);
    }

    private boolean contains(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
