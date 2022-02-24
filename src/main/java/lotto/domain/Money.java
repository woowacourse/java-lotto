package lotto.domain;

import lotto.Constant;

public class Money {
    private int money;
    private static final String NOT_INTEGER_ERROR_MESSAGE = "숫자가 아니에요";
    private static final String UNDER_MINIMUM_ERROR_MESSAGE = "1000원 미만인 입력 금액에 대한 예외처리";
    private static final String NOT_QUANTIZED_ERROR_MESSAGE = "거스름돈이 발생하는 경우에 대한 예외처리";

    public Money(String input) {
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
        if (value < Constant.UNIT_PRICE) {
            throw new IllegalArgumentException(UNDER_MINIMUM_ERROR_MESSAGE);
        }
    }

    private void isDivideByUnitPrice(int value) {
        if (value % Constant.UNIT_PRICE != 0) {
            throw new IllegalArgumentException(NOT_QUANTIZED_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
