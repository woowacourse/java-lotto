package lotto.domain;

import lotto.exception.IllegalMoneyException;

import java.util.regex.Pattern;

public class Money {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private static final int MINIMUM_MONEY = 1000;

    private final long value;

    public Money(String input) {
        validateMoneyValue(input);
        this.value = Long.parseLong(input);
    }

    public Money(long input) {
        this.value = input;
    }

    private void validateMoneyValue(String input) {
        validateNumberByFormat(input);
        validateMoneyByMiniMumLimit(input);

    }

    private void validateNumberByFormat(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalMoneyException(input + " : 양수가 아닙니다.");
        }
    }

    private void validateMoneyByMiniMumLimit(String input) {
        if (Integer.parseInt(input) < MINIMUM_MONEY) {
            throw new IllegalMoneyException(input + " : " + MINIMUM_MONEY + "보다 큰 금액을 입력해야 합니다.");
        }
    }

    public long getValue() {
        return value;
    }
}
