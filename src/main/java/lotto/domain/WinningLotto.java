package lotto.domain;

import java.util.List;

public class WinningLotto {

    private Lotto lotto;
    private int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        validateBonusNumber(lotto, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        lotto.checkDuplicate(bonusNumber);
    }
}
