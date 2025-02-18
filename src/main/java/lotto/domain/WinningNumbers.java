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
        if (winningNumbers.hasNumber(bonusNumber)) {
            throw new LottoException(MUST_NOT_BE_DUPLICATED_BONUS);
        }
    }

    public int checkMatchCount(Lotto lotto) {
        return winningNumbers.checkMatchCount(lotto);
    }

    public Rank match(Lotto lotto) {
        return Rank.checkPrize(checkMatchCount(lotto), checkMatchBonus(lotto));
    }

    public boolean checkMatchBonus(Lotto lotto) {
        return lotto.hasNumber(bonusNumber);
    }
}
