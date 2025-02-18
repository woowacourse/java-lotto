package model;

import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_NUMBER_MIN_RANGE;
import static constant.message.ExceptionMessage.DUPLICATE_BONUS_NUMBER;
import static constant.message.ExceptionMessage.INVALID_BONUS_FORMAT;
import static constant.message.ExceptionMessage.INVALID_BONUS_RANGE;
import static constant.message.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.pattern.InputPattern.INTEGER_PATTERN;

import util.Parser;

public class Bonus {

    private final int number;

    public static Bonus of(final String input, final Lotto lotto) {
        validateNullOrBlank(input, INVALID_INPUT_NULL_OR_BLANK.getMessage());
        validateInteger(input, INVALID_BONUS_FORMAT.getMessage());
        int number = Parser.convertStringToInteger(input);

        return new Bonus(number, lotto);
    }

    private Bonus(final int number, final Lotto lotto) {
        validateRange(number, LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE,
                INVALID_BONUS_RANGE.getMessage(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE));
        validateDuplicateLottoAndBonus(lotto, number, DUPLICATE_BONUS_NUMBER.getMessage());

        this.number = number;
    }

    public boolean isEqualTo(final int bonusNumber) {
        return ((Integer) number).equals(bonusNumber);
    }

    public boolean isContainedIn(final Lotto lotto) {
        return lotto.contains(number);
    }

    private static void validateNullOrBlank(final String input, final String errorMessage) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateInteger(final String input, final String errorMessage) {
        if (!input.matches(INTEGER_PATTERN.getContent())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateRange(final int input, final int minRange,
                                      final int maxRange, final String errorMessage) {
        if (minRange > input || input > maxRange) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateDuplicateLottoAndBonus(final Lotto lotto, final int bonus, final String errorMessage) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
