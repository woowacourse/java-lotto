package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static lotto.constant.ErrorMessage.LOTTO_NUMBER_DUPLICATE;
import static lotto.constant.ErrorMessage.LOTTO_NUMBER_SIZE;
import static lotto.constant.Limit.LOTTO_SIZE;
import static lotto.domain.Lotto.validateLottoNumber;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(final List<Integer> winningNumbers) {
        validateWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validateWinningNumbers(final List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE.getErrorMessage());
        }

        for (final int winningNumber : winningNumbers) {
            validateLottoNumber(winningNumber);
        }
    }

    public void validateBonusNumberDuplicated(final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATE.getErrorMessage());
        }
    }

    public int calculateMatchCount(final List<Integer> lottos) {
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottos);
        return matchNumbers.size();
    }
}
