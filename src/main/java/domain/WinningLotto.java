package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;

public class WinningLotto{
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    private Lotto lotto;
    private int bonus;

    public WinningLotto(String input, String bonus) {
        this.lotto = new Lotto(input);
        this.bonus = validateBonus(bonus);
    }

    public Rank countMatchNumbers(Lotto lotto) {
        int count = lotto.matchCount(this);
        return Rank.matchRank(count, lotto.isContains(bonus));
    }

    public boolean isContains(int num) {
        return lotto.isContains(num);
    }

    private int validateBonus(String input) {
        int bonus = validateIsInteger(input);
        validateRange(bonus);
        validateBonusDuplicate(bonus);
        return bonus;
    }

    private void validateBonusDuplicate(int bonus) {
        if(lotto.isContains(bonus)){
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private void validateRange(int num) {
        if (num < LOTTO_MIN || num > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
