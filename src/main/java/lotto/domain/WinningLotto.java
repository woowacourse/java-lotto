package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.lotto = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean isBonusMatched = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, isBonusMatched);
    }
}
