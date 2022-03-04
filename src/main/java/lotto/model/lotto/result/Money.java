package lotto.model.lotto.result;

import lotto.model.message.LottoCountExceptionMessage;

public class Money {
    private static final int NOTHING = 0;
    private static final int NON_REMAINDER = 0;
    private static final int UNIT = 1000;

    private final long number;

    public Money(long number) {
        validateInputZero(number);
        validateThousandUnitInputMoney(number);
        this.number = number;
    }

    private void validateInputZero(long money) {
        if (money == NOTHING) {
            throw new IllegalArgumentException(LottoCountExceptionMessage.LOWER_THAN_THOUSAND.getMassage());
        }
    }

    private void validateThousandUnitInputMoney(long money) {
        if (money % UNIT != NON_REMAINDER) {
            throw new IllegalArgumentException(LottoCountExceptionMessage.UNIT_ERROR.getMassage());
        }
    }

    public long getNumber() {
        return number;
    }
}
