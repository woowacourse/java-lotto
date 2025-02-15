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
        this.validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean hasBonusNumber = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, hasBonusNumber);
    }

    private void validateBonusNumber(int number) {
        if (this.lotto.hasNumber(number)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
        }
        if (number < MIN_BONUS_NUMBER || number > MAX_BONUS_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getContent());
        }
    }
}
