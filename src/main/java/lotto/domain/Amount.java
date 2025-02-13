package lotto.domain;

import static lotto.common.exception.ErrorMessage.*;

public class Amount {
    private final int money;

    public Amount(int money) {
        validateZero(money);
        validateDivided(money);
        this.money = money;
    }

    private void validateZero(int money) {
        if (isLessThousand(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_ZERO);
        }
    }

    private void validateDivided(int money) {
        if (isDivided(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIVIDED);
        }
    }

    private boolean isLessThousand(int money) {
        return money < 1000;
    }

    private boolean isDivided(int money) {
        return money % 1000 != 0;
    }

    public int getAmount() {
        return money / 1000;
    }

    public int getMoney() {
        return money;
    }
}
