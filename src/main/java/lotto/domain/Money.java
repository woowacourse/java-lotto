package lotto.domain;

import lotto.utils.Validator;

public class Money {
    private final int value;
    public Money(String input) {
        Validator.validateMoneyValue(input);
        this.value = Integer.parseInt(input);
    }

    public Money(int input) {
        this.value = input;
    }

    public int getValue() {
        return value;
    }
}
