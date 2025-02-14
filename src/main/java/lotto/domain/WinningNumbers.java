package lotto.domain;

import static lotto.exception.ErrorMessage.MUST_NOT_BE_DUPLICATED_BONUS;

import lotto.exception.LottoException;

public class WinningNumbers {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateExistBonusNumber();
    }

    private void validateExistBonusNumber() {
        if (winningNumbers.checkBonus(bonusNumber)) {
            throw new LottoException(MUST_NOT_BE_DUPLICATED_BONUS);
        }
    }

    public int checkMatchCount(Lotto lotto) {
        return winningNumbers.checkMatchCount(lotto);
    }

    public boolean checkMatchBonus(Lotto lotto) {
        return lotto.hasNumber(bonusNumber);
    }
}
