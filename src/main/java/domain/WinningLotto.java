package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;

public class WinningLotto{
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;

    private Lotto lotto;
    private int bonusNumber;

    public WinningLotto(final String input, final String bonusNumber) {
        this.lotto = new Lotto(input);
        this.bonusNumber = validateBonus(bonusNumber);
    }

    public Rank countMatchNumbers(final Lotto lotto) {
        int count = lotto.matchCount(this);
        return Rank.matchRank(count, lotto.contains(bonusNumber));
    }

    public boolean contains(final int num) {
        return lotto.contains(num);
    }

    private int validateBonus(final String input) {
        int bonus = validateIsInteger(input);
        validateRange(bonus);
        validateBonusDuplicate(bonus);
        return bonus;
    }

    private void validateBonusDuplicate(final int bonus) {
        if(lotto.contains(bonus)){
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private void validateRange(final int num) {
        if (num < LOTTO_MIN || num > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
