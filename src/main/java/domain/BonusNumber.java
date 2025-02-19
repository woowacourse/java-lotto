package domain;

import exception.AppException;

public class BonusNumber {

    public static final String INVALID_BONUS_NUMBER_RANGE = "보너스 번호는 1부터 45 사이 숫자여야 합니다.";
    public static final String INVALID_BONUS_NUMBER_FORMAT = "보너스 넘버는 숫자여야 합니다.";

    private final int value;

    public BonusNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int number) {
        if (number < Lotto.MIN_NUMBER || number > Lotto.MAX_NUMBER) {
            throw new AppException(INVALID_BONUS_NUMBER_RANGE);
        }
    }

    public int getValue() {
        return value;
    }
}
