package lotto.domain;

import java.util.List;
import lotto.exception_message.ExceptionMessage;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateRange(bonusNumber);
        validateOverlap(winningNumbers, bonusNumber);
        this.lotto = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean isBonusMatched = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, isBonusMatched);
    }


    public static void validateRange(int bonusNumber) {
        boolean isOutOfRange = bonusNumber < Lotto.MIN_LOTTO_NUMBER || bonusNumber > Lotto.MAX_LOTTO_NUMBER;
        if (isOutOfRange) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getContent());
        }
    }

    public static void validateOverlap(List<Integer> winningNumbers, int bonusNumber) {
        boolean isOverlap = winningNumbers.contains(bonusNumber);
        if (isOverlap) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
        }
    }
}
