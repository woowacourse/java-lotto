package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.util.Parser;

public class Money {

    private int money;
    private static final int THOUSAND = 1000;

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
        int validatedMoney = Parser.validateNumber(money, ErrorMessage.PURCHASE_FORMAT_ERROR.getMessage());
        validateUnit(validatedMoney);
        validateNegative(validatedMoney);
        validateLimit(validatedMoney);
        this.money = validatedMoney;
    }

    private void validateUnit(int validatedMoney) {
        if (validatedMoney % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_UNIT_ERROR.getMessage());
        }
    }
    private void validateNegative(int validatedMoney) {
        if (validatedMoney <= 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_MINIMUM_ERROR.getMessage());
        }
    }
    private void validateLimit(int validatedMoney) {
        if (validatedMoney > 100000) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_MAXIMUM_ERROR.getMessage());
        }
    }
}
