package lotto.domain;

import static lotto.common.constant.Constant.LOTTO_PRIZE;
import static lotto.common.exception.ErrorMessage.*;

public class Money {
    private final int money;

    public Money(int money) {
        validateEnoughMoney(money);
        validateDivided(money);
        this.money = money;
    }

    private void validateEnoughMoney(int money) {
        if (isLessThanLottoPrize(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_ZERO);
        }
    }

    private void validateDivided(int money) {
        if (isDivided(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIVIDED);
        }
    }

    private boolean isLessThanLottoPrize(int money) {
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
