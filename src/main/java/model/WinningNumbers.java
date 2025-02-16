package model;

import constants.ErrorType;
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

    public static WinningNumbers from(final List<Integer> numbers) {
        final List<LottoNumber> winningNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new WinningNumbers(winningNumbers);
    }

    private void validateSize(final List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != Lotto.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplicate(final List<LottoNumber> winningNumbers) {
        final Set<LottoNumber> deduplicatedLottoNumber = new HashSet<>(winningNumbers);
        if (deduplicatedLottoNumber.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }
    }

    public boolean containsLottoNumber(final LottoNumber lottoNumber) {
        return winningNumbers.contains(lottoNumber);
    }

}
