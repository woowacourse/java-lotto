package model;

import java.util.List;

public class BonusNumber {
    private int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("숫자는 1~45 사이여야 합니다.");
        }
    }

    public boolean isBonusNumber(Lotto lotto) {
        if (lotto.getRandomNumbers().contains(bonusNumber)) {
            return true;
        }
        return false;
    }

}
