package lotto.domain;

import lotto.constant.ExceptionMessage;
import lotto.constant.WinningTier;

public class WinningLotto {

    private static final int MIN_BONUS_NUMBER = 1;
    private static final int MAX_BONUS_NUMBER = 45;
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.validateBonusNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberRange(int number) {
        if (number < MIN_BONUS_NUMBER || number > MAX_BONUS_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getContent());
        }
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean isBonusMatched = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, isBonusMatched);
    }
}
