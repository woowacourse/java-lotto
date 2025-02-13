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
        if (number < 1 || number > 45) {
            throw new AppException(ErrorMessage.INVALID_BONUS_NUMBER_RANGE);
        }
    }

    public int getValue() {
        return value;
    }
}
