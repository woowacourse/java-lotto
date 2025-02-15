package model;

import static error.ErrorMessage.NUMBER_OUT_OF_RANGE;
import static error.ErrorMessage.WINNING_AND_BONUS_NUMBER_DUPLICATE;

import java.util.List;

public class WinningLotto {

    private Lotto basicLotto;
    private int bonusNumber;

    public WinningLotto(Lotto basicLotto, int bonusNumber) {
        validateBonusNumber(basicLotto, bonusNumber);
        this.basicLotto = basicLotto;
        this.bonusNumber = bonusNumber;
    }

    public int getMatchCount(List<Integer> numbers) {
        return basicLotto.countMatches(numbers);
    }

    public boolean isBonusMatched(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    private static void validateBonusNumber(Lotto basicLotto, int bonusNumber) {
        validateDuplicatedBonusNumber(basicLotto, bonusNumber);
        validateBonusNumberRange(bonusNumber);
    }

    private static void validateDuplicatedBonusNumber(Lotto basicLotto, int bonusNumber) {
        if (basicLotto.isBonusMatched(bonusNumber)) {
            throw new IllegalArgumentException(WINNING_AND_BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
