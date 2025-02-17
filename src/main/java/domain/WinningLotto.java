package domain;

import static util.constant.Message.*;
import static util.constant.Values.*;

import java.util.*;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningLotto(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public boolean containsWinningNumber(int number) {
        return winningNumbers.contains(number);
    }

    public boolean isBonusNumber(int number) {
        return bonusNumber == number;
    }

    private void validateWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateSize(winningNumbers);
        validateNumberRange(winningNumbers, bonusNumber);
        validateDuplicate(winningNumbers, bonusNumber);
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR);
        }
    }

    private void validateNumberRange(List<Integer> winningNumbers, int bonusNumber) {
        for (int number : winningNumbers) {
            checkNumberRange(number);
        }
        checkNumberRange(bonusNumber);
    }

    private void validateDuplicate(List<Integer> winningNumbers, int bonusNumber) {
        Set<Integer> set = new HashSet<>(winningNumbers);
        if (set.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE_ERROR);
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR);
        }
    }

    private void checkNumberRange(int number) {
        if (number < LOTTO_MIN_NUM || number > LOTTO_MAX_NUM) {
            throw new IllegalArgumentException(WINNING_NUMBER_RANGE_ERROR);
        }
    }
}
