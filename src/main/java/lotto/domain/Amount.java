package lotto.domain;

import static lotto.common.constant.ErrorMessage.*;

public class Amount {
    private static final int LOTTO_PRIZE = 1000;

    private final int money;

    public Amount(int money) {
        validateZero(money);
        validateDivided(money);
        this.money = money;
    }

    private void validateZero(int money) {
        if (isLessThousand(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_LESS_THEN_STANDARD.getMessage());
        }
    }

    private void validateDivided(int money) {
        if (isDivided(money)) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_MONEY.getMessage());
        }
    }

    private boolean isLessThousand(int money) {
        return money < LOTTO_PRIZE;
    }

    private boolean isDivided(int money) {
        return money % LOTTO_PRIZE != 0;
    }

    public int getAmount() {
        return money / LOTTO_PRIZE;
    }

    public int getMoney() {
        return money;
    }
}
