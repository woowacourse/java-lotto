package model;

import constans.ErrorType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private final List<LottoNumber> winningNumbers;

    public WinningNumbers(final List<LottoNumber> winningNumbers) {
        validateSize(winningNumbers);
        validateDuplicate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validateSize(final List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplicate(final List<LottoNumber> winningNumbers) {
        final Set<LottoNumber> set = new HashSet<>(winningNumbers);
        if (set.size() != 6) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }
    }
}
