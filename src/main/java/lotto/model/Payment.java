package lotto.model;

import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;

public class Payment {
    private static final int MINIMUM_PAYMENT = 1000;
    private static final int MAXINUM_PAYMENT = 100000;
    private static final int PAYMENT_UNIT = 1000;
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";
    private static final String OVER_RANGE_EXCEPTION_MESSAGE = "범위를 벗어났습니다.";
    private static final String UNIT_EXCEPTION_MESSAGE = "천 단위로 입력하세요.";

    public static int payment = 0;

    public Payment(String input) {
        int payment = isNotNumber(input);
        isValueRange(payment);
        isUnitK(payment);
        this.payment = payment;
    }

    private static int isNotNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static void isValueRange(int payment) {
        if (!(payment >= MINIMUM_PAYMENT && payment <= MAXINUM_PAYMENT)) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    private static void isUnitK(int payment) {
        if (!(payment % PAYMENT_UNIT == 0)) {
            throw new NotMultipleOfThousandException(UNIT_EXCEPTION_MESSAGE);
        }
    }
}
