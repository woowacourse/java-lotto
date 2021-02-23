package lotto.domain;

import static lotto.utils.Validation.isNumeric;

public class Money {

    private final int value;

    public Money(String moneyInput) {
        if (!isNumeric(moneyInput)) {
            throw new IllegalArgumentException();
        }

        int money = Integer.parseInt(moneyInput);
        if (money < 0) {
            throw new IllegalArgumentException();
        }

        value = money;
    }

    public Money(int moneyInput) {
        if (moneyInput < 0) {
            throw new IllegalArgumentException();
        }

        value = moneyInput;
    }

    public int getValue() {
        return value;
    }

}
