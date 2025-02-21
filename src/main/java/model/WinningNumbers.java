package model;

import error.ErrorMessage;

public class WinningNumbers {
    private final Lotto basicLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto basicLotto, int bonusNumber) {
        validateBonusNumber(basicLotto, bonusNumber);
        this.basicLotto = basicLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto basicLotto, int bonusNumber) {
        validateDuplicatedBonusNumber(basicLotto, bonusNumber);
        validateBonusNumberRange(bonusNumber);
    }

    private void validateDuplicatedBonusNumber(Lotto basicLotto, int bonusNumber) {
        if (basicLotto.isBonusMatched(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_AND_BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    public int getMatchCount(Lotto lotto) {
        return basicLotto.countMatches(lotto);
    }

    public boolean isBonusMatched(Lotto lotto) {
        return lotto.isBonusMatched(bonusNumber);
    }
}
