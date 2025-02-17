package lotto.domain;

import lotto.util.Parser;

public class Money {

    private static final int ZERO = 0;
    private static final int THOUSAND = 1000;
    private static final int MAXIMUM = 100000;
    private static final String PURCHASE_FORMAT_ERROR = "구입금액은 숫자여야 합니다.";
    private static final String PURCHASE_UNIT_ERROR = "구입금액은 천원 단위여야 합니다.";
    private static final String PURCHASE_MINIMUM_ERROR = "구입금액은 천원부터 입니다.";
    private static final String PURCHASE_MAXIMUM_ERROR = "10만원까지 구매 가능 합니다.";

    private int money;

    public Money(String money) {
        validate(money);
    }

    public double calculateProfit(int totalProfit) {
        return Math.floor((double) totalProfit /(money) * 100.0) / 100.0;
    }

    public int countsLotto() {
        return money / THOUSAND;
    }

    private void validate(String money) {
        int validatedMoney = Parser.validateNumber(money, PURCHASE_FORMAT_ERROR);
        validateUnit(validatedMoney);
        validateNegative(validatedMoney);
        validateLimit(validatedMoney);
        this.money = validatedMoney;
    }

    private void validateUnit(int validatedMoney) {
        if (validatedMoney % THOUSAND != 0) {
            throw new IllegalArgumentException(PURCHASE_UNIT_ERROR);
        }
    }

    private void validateNegative(int validatedMoney) {
        if (validatedMoney <= ZERO) {
            throw new IllegalArgumentException(PURCHASE_MINIMUM_ERROR);
        }
    }

    private void validateLimit(int validatedMoney) {
        if (validatedMoney > MAXIMUM) {
            throw new IllegalArgumentException(PURCHASE_MAXIMUM_ERROR);
        }
    }
}
