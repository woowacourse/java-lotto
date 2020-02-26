package lotto.model;

import java.util.Arrays;
import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;

public class Money {

    public static final int PAYMENT_UNIT = 1_000;
    private static final int PERCENT = 100;
    private static final int ZERO = 0;
    public static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";
    public static final String OVER_RANGE_EXCEPTION_MESSAGE = "범위를 벗어났습니다.";
    private static final String UNIT_EXCEPTION_MESSAGE = "천 단위로 입력하세요.";

    private int money;

    public Money(String input) {
        int money = validateNotNumber(input);
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

    private void validateUnitThousand(int payment) {
        if (!(payment % PAYMENT_UNIT == ZERO)) {
            throw new NotMultipleOfThousandException(UNIT_EXCEPTION_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }

    private double getRevenue() {
        return Arrays.stream(RankType.values())
            .mapToDouble(x -> x.getPrize() * LottoResult.lottoResultCount.get(x))
            .sum();
    }

    public int getYield() {
        return (int) (getRevenue() / money * PERCENT);
    }
}
