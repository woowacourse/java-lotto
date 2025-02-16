package model;

import constants.ErrorType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusBall;

    public WinningNumbers(final List<LottoNumber> winningNumbers, final LottoNumber bonusBall) {
        validateSize(winningNumbers);
        validateDuplicate(winningNumbers);
        validateBonusNumberDuplicate(winningNumbers, bonusBall);
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public static WinningNumbers of(final List<Integer> numbers, final int number) {
        final List<LottoNumber> winningNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        final LottoNumber bonusBall = new LottoNumber(number);
        return new WinningNumbers(winningNumbers, bonusBall);
    }

    private void validateSize(final List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != Lotto.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplicate(final List<LottoNumber> winningNumbers) {
        final Set<LottoNumber> removedDuplicate = new HashSet<>(winningNumbers);
        if (removedDuplicate.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }
    }

    private void validateBonusNumberDuplicate(final List<LottoNumber> winningNumbers, final LottoNumber bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(ErrorType.BONUS_BALL_IS_DUPLICATION.getMessage());
        }
    }

    public boolean containsLottoNumber(final LottoNumber lottoNumber) {
        return winningNumbers.contains(lottoNumber);
    }

    public boolean matchBonusNumber(final Lotto lotto) {
        return lotto.isContainsNumber(bonusBall);
    }
}
