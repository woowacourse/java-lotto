package lotto.model;

import lotto.model.number.LottoNumber;
import lotto.model.prize.MatchResult;

public class WinningLotto {
    private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        checkDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public MatchResult match(Lotto lotto) {
        return new MatchResult(lotto.match(winningLotto), lotto.contains(bonusNumber));
    }
}
