package lotto.domain;

import lotto.constant.ExceptionMessage;
import lotto.constant.WinningTier;

public class WinningLotto {

    private final int MIN_BONUS_NUMBER = 1;
    private final int MAX_BONUS_NUMBER = 45;
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.validateBonusNumberRange(bonusNumber, MIN_BONUS_NUMBER, MAX_BONUS_NUMBER);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberRange(int number, int min, int max) {
        if (number < min || number > max) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getContent());
        }
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean isBonusMatched = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, isBonusMatched);
    }
}
