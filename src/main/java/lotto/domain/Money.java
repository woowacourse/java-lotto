package lotto.domain;

import lotto.validator.InputValidator;

public class Money {
    public static final String ERROR_MESSAGE_NULL_POINT_MONEY = "입력값이 비었습니다.";
    private static final int LOTTO_PRICE = 1000;
    private static final int TO_PERCENT_VALUE = 100;
    public static final String ERROR_MESSAGE_MIN_MONEY = "천원 이상의 금액만 가능합니다.";
    private final int money;

    public Money(String inputMoney) {
        validateInputFormat(inputMoney);
        this.money = Integer.parseInt(inputMoney);
        validateOverThousand();
    }

    private void validateInputFormat(String inputMoney) {
        if (inputMoney == null || inputMoney.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_MONEY);
        }
        InputValidator.validateInteger(inputMoney);
    }

    private void validateOverThousand() {
        if (this.money < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MIN_MONEY);
        }
    }

    public int divideThousand() {
        return money / LOTTO_PRICE;
    }

    public int calculateIncomeRate(long income) {
        return (int) (income * TO_PERCENT_VALUE / money);
    }
}
