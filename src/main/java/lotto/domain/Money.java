package lotto.domain;

import lotto.utils.Validator;

public class Money {
    private final int value;
    public Money(String input) {
        Validator.validateMoneyValue(input);
        this.value = Integer.parseInt(input);
    }
}
