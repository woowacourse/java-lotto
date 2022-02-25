package lotto.model;

import java.util.List;

public class BonusNumber {

    private static final String BONUS_NUMBER_ERROR_MESSAGE = "[ERROR] 잘못된 보너스 번호입니다.";

    private int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        if (!isValidRange(bonusNumber)) {
            throw new RuntimeException(BONUS_NUMBER_ERROR_MESSAGE);
        }
    }

    private boolean isValidRange(int bonusNumber) {
        return bonusNumber >= 1 && bonusNumber <= 45;
    }

    public void hasDuplicateNumber(List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new RuntimeException(BONUS_NUMBER_ERROR_MESSAGE);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
