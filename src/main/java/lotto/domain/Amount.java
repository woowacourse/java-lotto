package lotto.domain;

import static lotto.exception.ErrorMessage.*;

public class Amount {
    private final int money;

    public Amount(int money) {
        validateZero(money);
        validateDivided1000(money);
        this.money = money;
    }

    private void validateZero(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(ERROR_MONEY_ZERO);
        }
    }

    private void validateDivided1000(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIVIDED);
        }
    }

    public int getAmount() {
        return money / 1000;
    }
}
