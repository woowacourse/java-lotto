package model;

import error.ErrorMessage;
import java.util.List;

public class WinningLotto {
    private static WinningLotto INSTANCE;
    private Lotto basicLotto;
    private int bonusNumber;

    private WinningLotto(Lotto basicLotto, int bonusNumber) {
        validateBonusNumber(basicLotto, bonusNumber);
        this.basicLotto = basicLotto;
        this.bonusNumber = bonusNumber;
    }

    public static void initialize(Lotto basicLotto, int bonusNumber) {
        if (INSTANCE == null) {
            INSTANCE = new WinningLotto(basicLotto, bonusNumber);
            return;
        }
        throw new IllegalStateException(ErrorMessage.WINNING_NUMBERS_ALREADY_DRAWN.getMessage());
    }

    public static WinningLotto getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException(ErrorMessage.WINNING_NUMBERS_NOT_DRAWN_YET.getMessage());
        }
        return INSTANCE;
    }

    private static void validateBonusNumber(Lotto basicLotto, int bonusNumber) {
        validateDuplicatedBonusNumber(basicLotto, bonusNumber);
        validateBonusNumberRange(bonusNumber);
    }

    private static void validateDuplicatedBonusNumber(Lotto basicLotto, int bonusNumber) {
        if (basicLotto.isBonusMatched(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_AND_BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    public int getMatchCount(List<Integer> numbers) {
        return basicLotto.countMatches(numbers);
    }

    public boolean isBonusMatched(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
