package lotto.domain;

import lotto.exception.BonusBallDuplicatedException;
import lotto.exception.BonusBallScopeException;

public class BonusBall {
    final int bonusNumber;

    public BonusBall(int bonusNumber, Lotto lotto) {
        validateBonusNumber(bonusNumber, lotto);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber, Lotto lotto) {
        validateBonusNumberDuplicated(bonusNumber, lotto);
        validateBonusNumberScope(bonusNumber);
    }

    private void validateBonusNumberDuplicated(int bonusNumber, Lotto lotto) {
        if (lotto.isContainNumber(bonusNumber)) {
            throw new BonusBallDuplicatedException();
        }
    }

    private void validateBonusNumberScope(int bonusNumber) {
        if (bonusNumber <= 0 || bonusNumber > 45) {
            throw new BonusBallScopeException();
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
