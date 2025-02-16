package lotto.domain;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean isBonusMatched = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, isBonusMatched);
    }
}
