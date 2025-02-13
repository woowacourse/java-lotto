package model;

import static model.ExceptionMessage.BONUS_DUPLICATE;
import static model.ExceptionMessage.INVALID_BONUS_RANGE;
import static model.ExceptionMessage.INVALID_BONUS_TYPE;

public class Bonus {

    private static final Integer BONUS_MIN_RANGE = 1;
    private static final Integer BONUS_MAX_RANGE = 45;

    private final Integer number;

    public static Bonus of(final String input, final Lotto lotto) {
        validateInteger(input);
        return new Bonus(Integer.parseInt(input), lotto);
    }

    public Bonus(final Integer number, final Lotto lotto) {
        validateRange(number);
        validateDuplicateWithLotto(number, lotto);
        this.number = number;
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_TYPE.getMessage());
        }
    }

    private void validateRange(final Integer number) {
        if ( BONUS_MIN_RANGE > number || number > BONUS_MAX_RANGE) {
            throw new IllegalArgumentException(INVALID_BONUS_RANGE.getMessage(BONUS_MIN_RANGE, BONUS_MAX_RANGE));
        }
    }

    private void validateDuplicateWithLotto(final Integer number, final Lotto lotto) {
        if (lotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE.getMessage());
        }
    }

    public Integer getNumber() {
        return number;
    }
}
