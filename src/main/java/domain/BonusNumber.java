package domain;

import error.AppException;
import error.ErrorMessage;

public class BonusNumber {

    private final int value;

    public BonusNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int number) {
        if (number < Lotto.MIN_NUMBER || number > Lotto.MAX_NUMBER) {
            throw new AppException(ErrorMessage.INVALID_BONUS_NUMBER_RANGE);
        }
    }

    public int getValue() {
        return value;
    }
}
