package lotto.domain;

import lotto.utils.Validator;

public class Money {
    private final long value;

    public Money(String input) {
        Validator.validateMoneyValue(input);
        this.value = Long.parseLong(input);
    }

    public Money(long input) {
        this.value = input;
    }

    public long getValue() {
        return value;
    }
}
