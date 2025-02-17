package lotto.domain;

import lotto.constant.ErrorMessage;

public class Money {

    private int money;
    private static final int ZERO = 0;
    private static final int THOUSAND = 1000;
    private static final int MAXIMUM = 100000;

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
        int validatedMoney = validateMoney(money, ErrorMessage.PURCHASE_FORMAT_ERROR.getMessage());
        validateUnit(validatedMoney);
        validateNegative(validatedMoney);
        validateLimit(validatedMoney);
        this.money = validatedMoney;
    }

    private int validateMoney(String money, String message) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }
    
    private void validateUnit(int validatedMoney) {
        if (validatedMoney % THOUSAND != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_UNIT_ERROR.getMessage());
        }
    }

    private void validateNegative(int validatedMoney) {
        if (validatedMoney <= ZERO) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_MINIMUM_ERROR.getMessage());
        }
    }

    private void validateLimit(int validatedMoney) {
        if (validatedMoney > MAXIMUM) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_MAXIMUM_ERROR.getMessage());
        }
    }

}
