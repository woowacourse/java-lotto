package lotto.domain;

import lotto.exception.DuplicateLottoNumberException;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new DuplicateLottoNumberException("로또숫자와 보너스숫자가 중복으로 입력되었습니다");
        }
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public int countMatchedLottoNumber(Lotto lotto) {
        return lotto.countMatchedLottoNumber(winningLotto);
    }

    public boolean contains(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
