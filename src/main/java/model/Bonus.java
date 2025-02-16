package model;

import static constant.ExceptionMessage.DUPLICATE_BONUS_NUMBER;
import static constant.ExceptionMessage.INVALID_BONUS_RANGE;
import static constant.ExceptionMessage.INVALID_BONUS_FORMAT;
import static constant.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_NUMBER_MIN_RANGE;

public class Bonus {

    private final Integer number;

    public static Bonus of(final String input, final Lotto lotto) {
        validateNullOrBlank(input);
        validateInteger(input);
        Integer number = parseInteger(input);
        return new Bonus(number, lotto);
    }

    private Bonus(final Integer number, final Lotto lotto) {
        validateRange(number);
        validateDuplicateWithLotto(number, lotto);
        this.number = number;
    }

    public boolean isEqualTo(int bonusNumber) {
        return number.equals(bonusNumber);
    }

    public boolean isContainedIn(Lotto lotto) {
        return lotto.contains(number);
    }

    private static void validateNullOrBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_FORMAT.getMessage());
        }
    }

    private static Integer parseInteger(final String input) {
        return Integer.parseInt(input);
    }

    private void validateRange(final Integer input) {
        if (LOTTO_NUMBER_MIN_RANGE > input || input > LOTTO_NUMBER_MAX_RANGE) {
            throw new IllegalArgumentException(
                    INVALID_BONUS_RANGE.getMessage(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE));
        }
    }

    private void validateDuplicateWithLotto(final Integer number, final Lotto lotto) {
        if (lotto.contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}
