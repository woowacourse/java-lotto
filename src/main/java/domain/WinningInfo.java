package domain;

import static domain.LottoRules.MAX_NUMBER;
import static domain.LottoRules.MIN_NUMBER;
import static error.ErrorMessage.BONUS_NUMBER_ALREADY_EXIST;
import static error.ErrorMessage.INVALID_NUMBER_RANGE;

public class WinningInfo {

    private final Lotto winningLotto;
    private final int bonusNumber;

    private WinningInfo(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningInfo of(Lotto numbers, int bonusNumber) {
        return new WinningInfo(numbers, bonusNumber);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validate(Lotto numbers, int bonusNumber) {
        validateNumbersRange(bonusNumber);
        validateBonusNumber(numbers, bonusNumber);
    }

    private void validateNumbersRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private void validateBonusNumber(Lotto numbers, int bonusNumber) {
        if (numbers.hasBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_EXIST.getMessage());
        }
    }
}
