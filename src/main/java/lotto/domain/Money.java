package lotto.domain;

import lotto.utils.StringUtil;

public class Money {

    private static final int DIVIDED_STANDARD = 1000;
    private static final int MIN_VALUE = 1000;
    private static final int MAX_VALUE = 2_000_000_000;
    private static final int REMAINDER = 0;
    private static final String VALUE_RANGE_ERROR_MESSAGE = "1000부터 20억의 숫자여야 합니다.";
    private static final String VALUE_DIVIDED_BY_ERROR_MESSAGE = "1000으로 나누어 떨어져야 합니다.";

    private final int value;

    public Money(String text) {
        int number = StringUtil.toInt(text);
        validate(number);
        this.value = number;
    }

    private void validate(int number) {
        validateValueRange(number);
        validateDividedBy(number);
    }

    private void validateValueRange(int number) {
        if (MIN_VALUE > number || number > MAX_VALUE) {
            throw new IllegalArgumentException(VALUE_RANGE_ERROR_MESSAGE);
        }
    }

    private void validateDividedBy(int number) {
        if (number % DIVIDED_STANDARD != REMAINDER) {
            throw new IllegalArgumentException(VALUE_DIVIDED_BY_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
