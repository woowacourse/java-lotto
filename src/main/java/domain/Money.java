package domain;

import exception.AppException;
import exception.ExceptionMessage;

public class Money {

    private static final int UNIT = 1_000;
    private final int amount;

    public Money(final int amount) {
        validateRange(amount);
        validateAmount(amount);
        this.amount = amount;
    }

    public int getCount() {
        return amount / UNIT;
    }

    private void validateAmount(final int amount) {
        if (amount % UNIT != 0) {
            throw new AppException(ExceptionMessage.INVALID_MONEY_UNIT);
        }
    }

    private void validateRange(final int amount) {
        if (amount < UNIT) {
            throw new AppException(ExceptionMessage.INVALID_MONEY_RANGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
