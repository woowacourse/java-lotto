package model;

import static constant.message.ExceptionMessage.DUPLICATE_BONUS_NUMBER;
import static constant.message.ExceptionMessage.INVALID_BONUS_RANGE;
import static constant.message.ExceptionMessage.INVALID_BONUS_FORMAT;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_NUMBER_MIN_RANGE;
import static constant.message.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;

import util.Parser;
import util.Validator;

public class Bonus {

    private final int number;

    public static Bonus of(final String input, final Lotto lotto) {
        Validator.validateNullOrBlank(input, INVALID_INPUT_NULL_OR_BLANK.getMessage());
        Validator.validateInteger(input, INVALID_BONUS_FORMAT.getMessage());
        int number = Parser.convertStringToInteger(input);

        return new Bonus(number, lotto);
    }

    private Bonus(final int number, final Lotto lotto) {
        Validator.validateRange(number, LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE,
                INVALID_BONUS_RANGE.getMessage(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE));
        Validator.validateDuplicateLottoAndBonus(lotto, number, DUPLICATE_BONUS_NUMBER.getMessage());

        this.number = number;
    }

    public boolean isEqualTo(final int bonusNumber) {
        return ((Integer) number).equals(bonusNumber);
    }

    public boolean isContainedIn(final Lotto lotto) {
        return lotto.contains(number);
    }
}
