package lotto.model;

import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;

public class Payment {
    private final int MINIMUM_PAYMENT = 1000;
    private final int MAXINUM_PAYMENT = 100000;
    private final int PAYMENT_UNIT = 1000;
    private final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";
    private final String OVER_RANGE_EXCEPTION_MESSAGE = "범위를 벗어났습니다.";
    private final String UNIT_EXCEPTION_MESSAGE = "천 단위로 입력하세요.";
    private int payment;

    public Payment(String input) {
        isNotNumber(input);
        int payment = Integer.parseInt(input);
        checkOverRange(payment);
        checkMultipleOfThousand(payment);
        this.payment = payment;
    }

    private void isNotNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void checkOverRange(int payment) {
        if (payment < MINIMUM_PAYMENT || payment > MAXINUM_PAYMENT) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    private void checkMultipleOfThousand(int payment) {
        if (payment % PAYMENT_UNIT != 0) {
            throw new NotMultipleOfThousandException(UNIT_EXCEPTION_MESSAGE);
        }
    }

    public int getPayment() {
        return payment;
    }

    public int getPaymentCount() {
        return payment / PAYMENT_UNIT;
    }
}
