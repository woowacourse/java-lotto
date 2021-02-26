package lotto.domain;

import static lotto.utils.Validation.isNumeric;

public class Money {
    private final int value;
    public Money(int money) {
        value = money;
    }

    public int getValue() {
        return value;
    }

}
