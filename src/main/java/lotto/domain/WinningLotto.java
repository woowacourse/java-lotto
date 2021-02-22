package lotto.domain;

import lotto.exception.LottoNumberException;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningNumbers, final LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new LottoNumberException("중복된 번호입니다.");
        }
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank matchRank(final Lotto lotto) {
        return LottoRank.of(winningNumbers.matchCount(lotto), lotto.contains(bonusNumber));
    }
}
