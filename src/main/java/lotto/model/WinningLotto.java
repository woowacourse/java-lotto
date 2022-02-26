package lotto.model;

import lotto.model.number.LottoNumber;
import lotto.model.number.WinningNumbers;
import lotto.model.prize.MatchResult;

/*
 * 지난 주 당첨 번호와 보너스 번호를 합쳐 당첨 로또정보를 가지는 class
 */
public class WinningLotto {
    private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        checkDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicate(WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public MatchResult match(Lotto lotto) {
        return new MatchResult(winningNumbers.match(lotto), lotto.contains(bonusNumber));
    }
}
