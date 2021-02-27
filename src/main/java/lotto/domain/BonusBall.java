package lotto.domain;

import lotto.exception.BonusBall.BonusBallDuplicatedException;
import lotto.exception.BonusBall.BonusBallScopeException;

public class BonusBall {
    private final int bonusNumber;

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
            throw new BonusBallDuplicatedException(bonusNumber);
        }
    }

    private void validateBonusNumberScope(int bonusNumber) {
        if (bonusNumber < LottoGenerator.LOTTO_START_NUMBER || bonusNumber > LottoGenerator.LOTTO_END_NUMBER) {
            throw new BonusBallScopeException(bonusNumber);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
