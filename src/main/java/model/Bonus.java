package model;

import static model.ExceptionMessage.BONUS_DUPLICATE;
import static model.ExceptionMessage.INVALID_BONUS_RANGE;

public class Bonus {

    private static final int BONUS_MIN_RANGE = 1;
    private static final int BONUS_MAX_RANGE = 45;

    private final int number;

    public static Bonus of(final int number, final Lotto lotto) {
        return new Bonus(number, lotto);
    }

    public Bonus(final int number, final Lotto lotto) {
        validateRange(number);
        validateDuplicateWithLotto(number, lotto);
        this.number = number;
    }

    private void validateRange(final int number) {
        if (BONUS_MIN_RANGE > number || number > BONUS_MAX_RANGE) {
            throw new IllegalArgumentException(INVALID_BONUS_RANGE.getMessage(BONUS_MIN_RANGE, BONUS_MAX_RANGE));
        }
    }

    private void validateDuplicateWithLotto(final int number, final Lotto lotto) {
        if (lotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }
}
