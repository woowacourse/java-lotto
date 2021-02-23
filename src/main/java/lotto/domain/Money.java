package lotto.domain;

import static lotto.utils.Validation.isNumeric;

public class Money {
    private static final String ERROR_MONEY_IS_NOT_NUMERIC = "[ERROR] 구입 금액은 숫자여야 합니다.";
    private static final String ERROR_MONEY_IS_NOT_POSITIVE = "[ERROR] 구입 금액은 음수여서는 안됩니다.";

    private final int value;

    public Money(String moneyInput) {
        if (!isNumeric(moneyInput)) {
            throw new IllegalArgumentException(ERROR_MONEY_IS_NOT_NUMERIC);
        }

        int money = Integer.parseInt(moneyInput);
        if (money < 0) {
            throw new IllegalArgumentException(ERROR_MONEY_IS_NOT_POSITIVE);
        }

        value = money;
    }

    public int getValue() {
        return value;
    }

}
