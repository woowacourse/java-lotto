package lotto.domain;

import lotto.Constant;

public class Money {
    private static final String NOT_INTEGER_ERROR_MESSAGE = "숫자가 아닙니다.";
    private static final String UNDER_MINIMUM_ERROR_MESSAGE = "1000원 미만의 금액은 입력할 수 없습니다.";
    private static final String NOT_QUANTIZED_ERROR_MESSAGE = "입력 금액은 1000원 단위여야 합니다.";
    private final int UNIT_PRICE;
    private int money;

    public Money(String input, int unitPrice) {
        UNIT_PRICE = unitPrice;
        isNumberFormat(input);
        int value = Integer.parseInt(input);
        isUnderMinimum(value);
        isDivideByUnitPrice(value);
        this.money = value;
    }

    private void isNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }

    private void isUnderMinimum(int value) {
        if (value < UNIT_PRICE) {
            throw new IllegalArgumentException(UNDER_MINIMUM_ERROR_MESSAGE);
        }
    }

    private void isDivideByUnitPrice(int value) {
        if (value % UNIT_PRICE != 0) {
            throw new IllegalArgumentException(NOT_QUANTIZED_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
