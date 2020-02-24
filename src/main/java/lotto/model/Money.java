package lotto.model;

import lotto.controller.LottoManager;
import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;

public class Money {

    public static final int PAYMENT_UNIT = 1000;
    private static final int MINIMUM_PAYMENT = 1000;
    private static final int MAXIMUM_PAYMENT = 100000;
    private static final int PERCENT = 100;
    private static final int ZERO = 0;
    public static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";
    public static final String OVER_RANGE_EXCEPTION_MESSAGE = "범위를 벗어났습니다.";
    private static final String UNIT_EXCEPTION_MESSAGE = "천 단위로 입력하세요.";

    private int money = 0;

    public Money(String input) {
        int money = validateNotNumber(input);
        validateValueRange(money);
        validateUnitThousand(money);
        this.money = money;
    }

    private int validateNotNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void validateValueRange(int payment) {
        if (!(payment >= MINIMUM_PAYMENT && payment <= MAXIMUM_PAYMENT)) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    private void validateUnitThousand(int payment) {
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
            prize += lottoResult.getPrize() * LottoManager.lottoResultMap.get(lottoResult.name());
        }
        return prize;
    }

    public int getYield() {
        return (int) (getRevenue() / money * PERCENT);
    }
}
