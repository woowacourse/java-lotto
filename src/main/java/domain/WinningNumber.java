package domain;

import exception.AppException;
import exception.ExceptionMessage;
import java.util.List;

public class WinningNumber {

    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumber(final Lotto winningLotto, final BonusNumber bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(final Lotto winningLotto, final BonusNumber bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber.getValue())) {
            throw new AppException(ExceptionMessage.INVALID_WINNING_NUMBER_DUPLICATE);
        }
    }

    public LottoRank calculateRankByLotto(final Lotto lotto) {
        final List<Integer> numbers = lotto.getNumbers();
        final boolean isBonus = numbers.contains(this.bonusNumber.getValue());
        return LottoRank.findByMatchedCountAndIsBonus(countMatchedNumbers(lotto), isBonus);
    }

    private int countMatchedNumbers(final Lotto lotto) {
        final List<Integer> numbers = lotto.getNumbers();
        return (int) numbers.stream()
                .filter(number -> winningLotto.getNumbers().contains(number))
                .count();
    }
}
