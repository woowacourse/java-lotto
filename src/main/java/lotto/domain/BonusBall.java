package lotto.domain;

import lotto.exception.BonusBallException;

public class BonusBall {
    int bonusNumber;

    public BonusBall(int bonusNumber, Lotto lotto) {
        validateBonusNumberDuplicated(bonusNumber, lotto);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberDuplicated(int bonusNumber, Lotto lotto) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new BonusBallException();
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
