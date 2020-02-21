package lotto.model;

import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;

public class Money {

    private static final int MINIMUM_PAYMENT = 1000;
    private static final int MAXIMUM_PAYMENT = 100000;
    private static final int PAYMENT_UNIT = 1000;
    private static final int PERCENT = 100;
    private static final int ZERO = 0;
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";
    private static final String OVER_RANGE_EXCEPTION_MESSAGE = "범위를 벗어났습니다.";
    private static final String UNIT_EXCEPTION_MESSAGE = "천 단위로 입력하세요.";

    private int money = 0;

    public Money(String input) {
        int money = isNotNumber(input);
        isValueRange(money);
        isUnitK(money);
        this.money = money;
    }

    private int isNotNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void isValueRange(int payment) {
        if (!(payment >= MINIMUM_PAYMENT && payment <= MAXIMUM_PAYMENT)) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    private void isUnitK(int payment) {
        if (!(payment % PAYMENT_UNIT == ZERO)) {
            throw new NotMultipleOfThousandException(UNIT_EXCEPTION_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }

    private double getRevenue() {
        double prize = 0;
        for (LottoResult lottoResult : LottoResult.values()) {
            prize += lottoResult.prizeResult();
        }
        return prize;
    }

    public int getYield() {
        return (int) (getRevenue() / money * PERCENT);
    }
}
