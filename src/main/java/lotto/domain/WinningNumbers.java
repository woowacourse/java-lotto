package lotto.domain;

import static lotto.exception.ErrorMessage.MUST_NOT_BE_DUPLICATED;
import static lotto.exception.ErrorMessage.OUT_OF_RANGE;
import static lotto.exception.ErrorMessage.SIZE_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoException;

public class WinningNumbers {

    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 45;
    private final int LOTTO_SIZE = 6;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateWinningNumberRange();
        validateBonusNumberRange();
        validateWinningNumberSize();
        validateDuplicateWinningNumber();
        validateExistBonusNumber();
    }

    private void validateWinningNumberRange() {
        for (Integer winningNumber : winningNumbers) {
            if (winningNumber < MIN_NUMBER || winningNumber > MAX_NUMBER) {
                throw new LottoException(OUT_OF_RANGE);
            }
        }
    }

    private void validateBonusNumberRange() {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new LottoException(OUT_OF_RANGE);
        }
    }

    private void validateDuplicateWinningNumber() {
        Set<Integer> set = new HashSet<>(winningNumbers);
        if (set.size() != LOTTO_SIZE) {
            throw new LottoException(MUST_NOT_BE_DUPLICATED);
        }
    }

    private void validateWinningNumberSize() {
        if (winningNumbers.size() != LOTTO_SIZE) {
            throw new LottoException(SIZE_ERROR);
        }
    }

    private void validateExistBonusNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new LottoException(MUST_NOT_BE_DUPLICATED);
        }
    }


    public int checkMatchCount(Lotto lotto) {
        return lotto.checkMatchCount(winningNumbers);
    }

    public boolean checkMatchBonus(Lotto lotto) {
        return lotto.checkBonus(bonusNumber);
    }
}
